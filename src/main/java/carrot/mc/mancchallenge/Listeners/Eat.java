package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static carrot.mc.mancchallenge.Utils.Chat.color;

public class Eat implements Listener {

    @EventHandler
    private static void eat(PlayerItemConsumeEvent e){
        Player target = e.getPlayer();
        ItemStack item = e.getItem();
        if(item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)){
            PlayerData.setRegean(target, 20);
            target.sendMessage(color("&aAhora tienes 20s de regeneración por la notch!"));
            PlayerData.setNotchUse(target, PlayerData.getNotchUse(target) + 1);
        }
        if(item.getType().equals(Material.GOLDEN_APPLE) && item.hasItemMeta()){
            if(Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(color("&6Super Golden Apple +"))) return;
            if(item.containsEnchantment(Enchantment.ARROW_INFINITE))
                PlayerData.setRegean(target, 5); // 5 seconds for hyper golden apple +
        }
        if(item.getType().equals(Material.MILK_BUCKET))
            PlayerData.setRegean(target, 0);
    }

}
