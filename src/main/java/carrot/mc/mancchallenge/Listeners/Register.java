package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Register {

    public static void registerListeners(){
        addListener(
                new Totem(),
                new Join(),
                new Regen(),
                new Death(),
                new Eat(),
                new Damage(),
                new Retos()
        );
    }

    // Utility method to register listeners
    private static void addListener(Listener... listeners){
        for(Listener listener : listeners){
            Bukkit.getPluginManager().registerEvents(listener, Main.getPlugin());
        }
    }

}
