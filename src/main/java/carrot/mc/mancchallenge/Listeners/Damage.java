package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {

    @EventHandler
    private void onDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player target = (Player) e.getEntity();
        PlayerData.addDamage(target, (int) e.getFinalDamage());
    }

}
