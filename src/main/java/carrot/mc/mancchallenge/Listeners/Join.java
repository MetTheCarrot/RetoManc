package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Task.Score;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import static carrot.mc.mancchallenge.Utils.Chat.color;

public class Join implements Listener {

    @EventHandler
    private static void firstJoin(PlayerJoinEvent e){
        Player target = e.getPlayer();
        Score.createScoreboard(target);
        if(target.hasPlayedBefore()) return;
        target.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
        target.sendMessage(color("&aBienvenido a MancChallenge!"));
    }

}
