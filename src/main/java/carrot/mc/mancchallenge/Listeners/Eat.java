package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class Eat implements Listener {

    @EventHandler
    private static void eat(PlayerItemConsumeEvent e){
        Player target = e.getPlayer();
        ItemStack item = e.getItem();
        if(item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)){
            PlayerData.setRegean(target, 20);
            PlayerData.setNotchUse(target, PlayerData.getNotchUse(target) + 1);
        }
        if(item.getType().equals(Material.MILK_BUCKET))
            PlayerData.setRegean(target, 0);
    }

}
