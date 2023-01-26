package carrot.mc.mancchallenge.Utils;

import carrot.mc.mancchallenge.Data.CreateFile;
import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Task.Pause;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static carrot.mc.mancchallenge.Utils.Timestamp.*;
import static carrot.mc.mancchallenge.Utils.ConvertTypesUtils.*;

import static carrot.mc.mancchallenge.Task.TRetos.taskRetos;
import static carrot.mc.mancchallenge.Utils.Chat.broadCast;
import static carrot.mc.mancchallenge.Utils.PlayerData.updateDay;
import static carrot.mc.mancchallenge.Utils.Timestamp.getNowTimestamp;

public class Day {

    public static void mensaje(String message){
        for(Player target : Bukkit.getOnlinePlayers())
            target.sendMessage(message);
    }

    private static final boolean debug = false;

    private static final CreateFile data = new CreateFile(Main.getPlugin(),"day.yml");

    public static void setData(String id, String value){
        data.setConfig(id, value);
    }

    public static String getData(String id, String defaultValue){
        if(data.getConfig().getString(id) == null){
            setData(id, defaultValue);
        }
        return data.getConfig().getString(id);
    }

    // First time

    public static void checkFirstTime(){
        getData("startTimestamp", getNowTimestamp());
    }

    // Pausas

    public static boolean isPause(){
        return bool(getData("pause", "false"));
    }

    public static void setPause(boolean pause){
        setData("pause", str(pause));
        setData("totalPauses", str(getTotalPauses() + 1));
        if(debug) mensaje("Total pauses: " + getTotalPauses());
        if(pause) startTempPauseTime();
        else stopTempPauseTime();
        if(pause){
            Pause.pauseTask();
        } else{
            updateDayPDC(getDay());
            taskRetos();
        }
    }

    public static int getTotalPauses(){
        return integer(getData("totalPauses", "0"));
    }

    // Day

    public static int getDay(){
        int day =  integer(getData("day", "1"));
        if(day == 0) forceSetDay(1);
        return day;
    }

    public static void forceSetDay(int day){
        setGlobalTimePlayed(day * 86400);
    }

    public static void updateDayPDC(int day){
        int dayPDC = (int) (10 * (Math.floor(day / 3.0)));
        broadCast("&a&l¡El día ha cambiado! &7Ahora es el día &a" + day + " &7y el PDC es de &a" + dayPDC + " &7días.");
        broadCast("&7Recuerda actualizar el servidor para aplicar los cambios correctamente.");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pdc cambiardia " + dayPDC);
    }

    public static String diferenciaParaLlegarAlOtroDia(){
        return String.valueOf((86400 * getDay()) - getGlobalTimePlayed());
    }

    // Global Time Played

    public static int getGlobalTimePlayed(){
        return integer(getData("globalTimePlayed", "0"));
    }

    public static void setGlobalTimePlayed(int time){
        setData("globalTimePlayed", str(time));
    }

    // Global Pause Time

    public static int getGlobalPauseTime(){
        return integer(getData("globalPauseTime", "0"));
    }

    public static void setGlobalPauseTime(int time){
        setData("globalPauseTime", str(time));
        if(debug) mensaje("Global pause time: " + getGlobalPauseTime());
    }

    // Cache tiempo de pausa

    public static void startTempPauseTime(){
        setData("tempPauseTime", getNowTimestamp());
    }

    public static void stopTempPauseTime(){
        long difference = Timestamp.differenceTime(getData("tempPauseTime", "0"), getNowTimestamp());
        setData("tempPauseTime", "0");
        setGlobalPauseTime((int) (getGlobalPauseTime() + difference));
        if(debug) mensaje("Temp pause time: " + difference);
    }

    // Formato

    public static String formatTiempoDeInicio(){
        String startTimestamp = getData("startTimestamp", getNowTimestamp());
        return TimestampWithDay(str(differenceTime(startTimestamp, getNowTimestamp())));
    }

    public static String formatTiempoDeJuego(){
        return TimestampWithDay(str(getGlobalTimePlayed() - 86400));
    }

    public static String formatTiempoDePausa(){
        long cacheTime = 0;
        if(isPause())
            cacheTime = Timestamp.differenceTime(getData("tempPauseTime", "0"), getNowTimestamp());
        return TimestampWithDay(str(getGlobalPauseTime() + cacheTime));
    }

    public static String formatTiempoDePausaTemporal(){
        if(isPause())
            return TimestampWithDay(str(differenceTime(getData("tempPauseTime", "0"), getNowTimestamp())));
        else
            return TimestampWithDay(str(0));
    }

    // Task update

    public static void update(){
        if(debug){
            mensaje("-----");
            mensaje("Pausado? " + isPause());
            mensaje("Dia: " + getDay());
            mensaje("Tiempo de inicio: " + formatTiempoDeInicio());
            mensaje("Tiempo de juego: " + formatTiempoDeJuego());
            mensaje("Tiempo de pausa global: " + formatTiempoDePausa());
            mensaje("Total de pausas: " + getTotalPauses());
            mensaje("Tiempo de pausa temporal: " + formatTiempoDePausaTemporal());
            mensaje("-----");
        }
        if(!isPause())
            setGlobalTimePlayed(getGlobalTimePlayed() + 1);
        int currentDay = getDay();
        int newDay = (int) Math.floor(getGlobalTimePlayed()/86400.0);
        if(currentDay != newDay){
            updateDayPDC(newDay);
            checkReto();
            for(Player target: Bukkit.getOnlinePlayers()){
                updateDay(target, newDay);
            }
            setData("day", str(newDay));
            mensaje("Dia " + newDay);
        }
    }

    public static void taskDay(){
        checkFirstTime();
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(Main.getPlugin(), 0, 20L);
    }

    public static void checkReto(){
        for(Player target: Bukkit.getOnlinePlayers()){
            if(getDay() == 0 || getDay() == 1) return;
            // Si el jugador no ha completado el reto del día anterior, se le marca como muerto
            updateDay(target, getDay());
            if(!PlayerData.isComplete(target, PlayerData.getDaySurvived(target))) PlayerData.setDeath(target);
        }
    }

}
