package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class Regen implements Listener {


    @EventHandler
    private void cancelRegen(EntityRegainHealthEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player target = (Player) e.getEntity();
        if(PlayerData.canRegean(target)) e.setAmount(1); // La regeneracion 2 cura 1 corazon por segundo
        else e.setCancelled(true);
    }

}
