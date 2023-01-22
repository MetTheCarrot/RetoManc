package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static carrot.mc.mancchallenge.Utils.Chat.color;
import static carrot.mc.mancchallenge.Utils.Day.*;
import static carrot.mc.mancchallenge.Utils.PlayerData.isComplete;

public class Bossbar {

    public static String getReto(int day){
        String reto = "Sobrevive";
        if(day == 1) reto = "Crea una tarta"; // check
        else if(day == 2) reto = "Consigue una poción de respiración";
        else if(day == 3) reto = "Consigue 1 stack de obsidiana";
        else if(day == 4) reto = "Mata 50 arañas";
        else if(day == 5) reto = "Mata 15 arañas de cueva";
        else if(day == 6) reto = "Realiza una poción de visión nocturna";
        else if(day == 7) reto = "Realiza una poción de maestro tortuga con mas duración";
        else if(day == 8) reto = "Invoca un gólem de hierro";
        else if(day == 9) reto = "Consigue la armadura de Netherite.";
        else if(day == 10) reto = "Mata al &cPermadeath Demon";
        else if(day == 11) reto = "Consigue elytras";
        else if(day == 12) reto = "Consigue 2 shulkers y quémalos";
        else if(day == 13) reto = "No craftear la reliquia del fin (Hasta dia 14) y matar 20 EnderCreepers";
        else if(day == 14) reto = "Rompe una elytra";
        else if(day == 15) reto = "Mata cada uno de los esqueletos clase.";
        else if(day == 16) reto = "Completa una raid nivel 5.";
        else if(day == 17) reto = "Consigue 1 stack de diamantes";
        else if(day == 18) reto = "Mata a todos los tipos de esqueleto";
        else if(day == 19) reto = "Entra al The Beginning";
        else if(day == 20) reto = "Mata un Wither Boss";
        else if(day == 21) reto = "Mata un Ender Quantum Creeper con la espada";

        return reto;
    }

    public static BarColor getColor(){
        int day = getDay();
        double retos = 5 / 21.0; // 5 colores para 21 retos
        int progreso = (int) Math.floor(retos * day);
        BarColor colorElegido = BarColor.GREEN; // 0 - 1
        if(progreso == 2) return BarColor.YELLOW;
        else if (progreso == 3) return BarColor.PINK;
        else if (progreso == 4) return BarColor.PURPLE;
        else if (progreso >= 5) return BarColor.RED;
        return colorElegido;
    }

    private static final NamespacedKey bossbar_key = BossbarBuilder.setKey("mancchallenge_bossbar");

    public static double getProgress(){
        try{
            int tiempoProximoDia = Integer.parseInt(diferenciaParaLlegarAlOtroDia());
            int days = getDay();
            return ((1.0/86400) * tiempoProximoDia) + days;
        } catch(Exception e){
            return 0;
        }
    }

    public static String getTitle(Player target){
        return Chat.formatColor() + "Día " + getDay() + " &7- &f" + (isComplete(target,getDay()) ? "&m" : "") + getReto(getDay()) + "&r &7- &f" + Timestamp.TimestampToHour(Day.diferenciaParaLlegarAlOtroDia());
    }

    public static BossBar getBar(Player target){
        return new BossbarBuilder(bossbar_key)
                .setTitle(getTitle(target))
                .setColor(getColor())
                .setStyle(BarStyle.SOLID)
                .build();
    }

    public static BossBar getSingleBar(Player target){
        return target.getServer().getBossBar(bossbar_key);
    }

    public static void addPlayerToBar(Player target){
        try{
            getSingleBar(target).removePlayer(target);
        } catch(Exception e){
            getBar(target).addPlayer(target);
        }
        getBar(target).addPlayer(target);
    }

    public static void removePlayerFromBar(Player target){
        getSingleBar(target).removePlayer(target);
        getBar(target).removePlayer(target);
    }

    public static void removeAllPlayersFromBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            removePlayerFromBar(player);
        }
    }

    public static void addAllPlayersToBar(){
        for(Player player : Bukkit.getOnlinePlayers())
            addPlayerToBar(player);
    }

    public static void updateBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            BossBar bar = getSingleBar(player);
            if(bar == null) addPlayerToBar(player);
            bar.setTitle(color(getTitle(player)));
            bar.setColor(getColor());
            bar.setProgress(getProgress());
        }
    }

    public static void bossbarTask(){
        new BukkitRunnable(){
            @Override
            public void run() {
                updateBar();
            }
        }.runTaskTimer(Main.getPlugin(), 0, 20L);
    }

}
