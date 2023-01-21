package carrot.mc.mancchallenge.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Chat {

    public static String color(String message){
        return message.replace("&", "§");
    }

    public static void broadCast(String message){
        for(Player target : Bukkit.getOnlinePlayers())
            target.sendMessage(color(message));
    }

    public static String formatColor(){
        int day = Day.getDay();
        double retos = 8 / 21.0; // 8 colores para 21 retos
        int progreso = (int) Math.floor(retos * day);
        String colorElegido = "&a"; // 0 - 1
        if(progreso == 2) colorElegido = "&2";
        else if (progreso == 3) colorElegido = "&e";
        else if (progreso == 4) colorElegido = "&6";
        else if (progreso == 5) colorElegido = "&d";
        else if (progreso == 6) colorElegido = "&5";
        else if (progreso == 7) colorElegido = "&8";
        else if (progreso >= 8) colorElegido = "&0";

        return color(colorElegido);
    }

    public static String getPrefix(){
        return color("&8[&6MancChallenge&8] &7");
    }

}

