package carrot.mc.mancchallenge.Utils;

import carrot.mc.mancchallenge.Data.CreateFile;
import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Task.Pause;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static carrot.mc.mancchallenge.Utils.Chat.broadCast;
import static carrot.mc.mancchallenge.Utils.PlayerData.getDaySurvived;
import static carrot.mc.mancchallenge.Utils.PlayerData.updateDay;
import static carrot.mc.mancchallenge.Utils.Timestamp.getNowTimestamp;

public class Day {

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

    public static void reload(){
        data.reloadConfig();
    }

    public static void setPause(boolean pause){
        if(pause){
            setData("pause", "true");
            startTempPauseTime();
            Pause.pauseTask();
            addTotalPauses();
        } else{
            setData("pause", "false");
            stopTempEndPauseTime();
        }
    }

    public static int getTotalPauses(){
        return Integer.parseInt(getData("totalPauses", "0"));
    }

    public static void addTotalPauses(){
        setData("totalPauses", String.valueOf(getTotalPauses() + 1));
    }

    public static boolean isPause(){
        return Boolean.parseBoolean(getData("pause", "false"));
    }

    public static int getDay(){
        return Integer.parseInt(getData("day", "1"));
    }

    public static void setDay(int day){
        setData("day", String.valueOf(day));
    }

    public static void updateDayPDC(int day){
        int dayPDC = (int) (10 * (Math.floor(day / 3.0)));
        broadCast("&a&l¡El día ha cambiado! &7Ahora es el día &a" + day + " &7y el PDC es de &a" + dayPDC + " &7días.");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pdc cambiardia " + dayPDC);
    }

    public static void startTempPauseTime(){
        setData("cache.pauseTime", "0");
    }

    public static void updateTempPauseTime(){
        long pauseTime = Long.parseLong(getData("cache.pauseTime", "0")) + 1;
        if(isPause()){
            setData("cache.pauseTime", String.valueOf(pauseTime));
            addTimeGlobalPlayedTime(1);
        }
    }

    public static void stopTempEndPauseTime(){
        int pauseTime = Integer.parseInt(getData("cache.pauseTime", "0"));
        addTimeGlobalPauseTime(pauseTime); // add time to global pause time
        addTimeGlobalPlayedTime(pauseTime); // add time to global played time
        setData("cache.pauseTime", "0");
    }

    public static int getTotalTimeGlobalPauseTime(){
        return Integer.parseInt(getData("pauseTime", "0"));
    }

    public static void addTimeGlobalPauseTime(int seconds){
        setData("pauseTime", String.valueOf(getTotalTimeGlobalPauseTime() + seconds));
    }

    public static String formatTotalTimeGlobalPauseTime(){
        return Timestamp.TimestampToHour(String.valueOf(getTotalTimeGlobalPauseTime()));
    }

    public static int getTotalTimeGlobalPlayedTime(){
        return Integer.parseInt(getData("playedTime", getNowTimestamp()));
    }

    public static String formatTotalTimeGlobalPlayedTime(){
        return Timestamp.getDifferenceTime(String.valueOf(getTotalTimeGlobalPlayedTime()), getNowTimestamp());
    }

    public static void addTimeGlobalPlayedTime(int seconds){
        setData("playedTime", String.valueOf(getTotalTimeGlobalPlayedTime() + seconds));
    }

    public static void debug(int seconds){
        int add = Integer.parseInt(getNowTimestamp()) - seconds;
        setData("playedTime", String.valueOf(add));
    }

    public static void checkTime(){
        getData("startTimestamp", getNowTimestamp());
    }

    public static String getStartTime(){
        String startTime = getData("startTimestamp", getNowTimestamp());
        broadCast("Timestamp inicio:" + startTime);
        return Timestamp.getDifferenceTime(startTime, getNowTimestamp());
    }

    public static void checkDay(){
        int day = getDay();
        double playedTime = Long.parseLong(getNowTimestamp()) - getTotalTimeGlobalPlayedTime();
        int dayTime = (int) Math.floor(playedTime / 86400);
        setDay(dayTime);
        if(dayTime > day){
            broadCast("&aEl día ha cambiado a " + dayTime + "!");
            checkReto();
            updateDayPDC(dayTime);
            for(Player target: Bukkit.getOnlinePlayers()){
                updateDay(target, dayTime);
            }
        }
    }

    public static void checkReto(){
        for(Player target: Bukkit.getOnlinePlayers()){
            // Si el jugador no ha completado el reto del día anterior, se le marca como muerto
            target.sendMessage("Dia sobrevivido: " + PlayerData.getDaySurvived(target));
            updateDay(target, getDay());
            target.sendMessage("Completo el reto del día anterior (" + getDaySurvived(target) + "): " + PlayerData.isComplete(target, PlayerData.getDaySurvived(target)));
            if(!PlayerData.isComplete(target, PlayerData.getDaySurvived(target))) PlayerData.setDeath(target);
        }
    }

    public static String diferenciaParaLlegarAlOtroDia(){
        int playedTime = Integer.parseInt(getNowTimestamp()) - getTotalTimeGlobalPlayedTime();
        return String.valueOf(86400 - playedTime);
    }

    public static void taskDay(){
        new BukkitRunnable(){
            @Override
            public void run() {

                checkDay();
                updateTempPauseTime();

            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

}
