package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Task.Bossbar;
import carrot.mc.mancchallenge.Task.Score;
import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import static carrot.mc.mancchallenge.Utils.Chat.color;
import static carrot.mc.mancchallenge.Utils.Day.checkReto;
import static carrot.mc.mancchallenge.Utils.Day.getDay;

public class Join implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void firstJoin(PlayerJoinEvent e){
        Player target = e.getPlayer();
        Score.createScoreboard(target);
        Bossbar.addPlayerToBar(target);
        PlayerData.updateDay(target, getDay());
        checkReto();
        if(target.hasPlayedBefore()) return;
        target.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
        target.sendMessage(color("&aBienvenido a MancChallenge!"));
    }

    @EventHandler
    private static void leave(PlayerQuitEvent e){
        PlayerData.updateDay(e.getPlayer(), getDay());
        Bossbar.removePlayerFromBar(e.getPlayer());
    }

}
