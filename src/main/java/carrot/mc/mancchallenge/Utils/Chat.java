package carrot.mc.mancchallenge.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Chat {

    public static String color(String message){
        return message.replace("&", "§");
    }

    public static void broadCast(String message){
        for(Player target : Bukkit.getOnlinePlayers())
            target.sendMessage(color(message));
    }

    public static String getPrefix(){
        return color("&8[&6MancChallenge&8] &7");
    }

    public static String formatTimePlayed(Player target){
        int seconds = (target.getStatistic(org.bukkit.Statistic.PLAY_ONE_MINUTE) / 20);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Atlantic/Madeira")); // UTC
        return format.format(seconds * 1000L);
    }

}

