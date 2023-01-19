package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.Chat;
import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class Totem implements Listener {

    @EventHandler
    private static void useTotem(EntityResurrectEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player target = (Player) e.getEntity();
        int totem = PlayerData.getUseTotem(target) + 1;
        PlayerData.setTotem(target, totem);
        Chat.broadCast("&e" + target.getName() + " &7has used &e" + totem + " &7totem" + (totem == 1 ? "" : "s") + "!");
        PlayerData.setRegean(target, 40); //40 seconds da el totem
        if(totem >= 3){
            Chat.broadCast("&e" + target.getName() + " &7lost 3 totems and is now dead!");
            target.setGameMode(GameMode.SPECTATOR);
        }
    }

}
