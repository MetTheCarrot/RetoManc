package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Discord.DiscordBot;
import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class Totem implements Listener {

    private static boolean haveTotem(Player target){
        return (target.getInventory().getItemInOffHand().getType().equals(org.bukkit.Material.TOTEM_OF_UNDYING) || target.getInventory().getItemInMainHand().getType().equals(org.bukkit.Material.TOTEM_OF_UNDYING));
    }

    @EventHandler
    private static void useTotem(EntityResurrectEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player target = (Player) e.getEntity();
        if(!haveTotem((Player) e.getEntity())) return;
        int totem = PlayerData.getUseTotem(target) + 1;
        PlayerData.setTotem(target, totem);
        PlayerData.setRegean(target, 40);
        if(totem > 3)
            e.setCancelled(true);
        DiscordBot.sendTotemMessage(e);
    }

}
