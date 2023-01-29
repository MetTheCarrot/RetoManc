package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.Day;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Pause {

    public static void pauseTask(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!Day.isPause()) cancel();
                else {
                    for(World world : Bukkit.getWorlds()){
                        for(Entity entity : world.getEntities()){
                            entity.teleport(entity.getLocation());
                            if(entity instanceof Player){
                                Player player = (Player) entity;
                                player.sendTitle("§cEl juego esta en pausa", "§7Usa /pause para volver", 0, 20, 0);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getPlugin(), 0, 0L);
    }

}
