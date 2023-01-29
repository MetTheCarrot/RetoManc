package carrot.mc.mancchallenge.Utils;

import carrot.mc.mancchallenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Chat {

    public static String color(String message){
        return message.replace("&", "§");
    }

    public static void broadCast(String message){
        for(Player target : Bukkit.getOnlinePlayers())
            target.sendMessage(color(message));
    }

    public static String removeFormat(String text) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
        for (String s : list) {
            text = text.replace("&" + s, "");
        }
        return text;
    }

    public static void console(Level level, String message){
        Main.getPlugin().getLogger().log(level, color(getPrefix() + "&r" + message));
    }

    public static String formatColor(){
        int day = Day.getDay();
        double retos = 8 / 21.0; // 8 colores para 21 retos
        int progreso = (int) Math.floor(retos * day);
        switch(progreso){
            case 0:
            case 1:
                return "&a";
            case 2:
                return "&2";
            case 3:
                return "&e";
            case 4:
                return "&6";
            case 5:
                return "&d";
            case 6:
                return "&5";
            case 7:
                return "&8";
            default: return "&0";
        }
    }

    public static String getPrefix(){
        return color("&8[&6MancChallenge&8] &7");
    }

}

