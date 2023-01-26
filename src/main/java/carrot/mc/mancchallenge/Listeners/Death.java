package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Discord.DiscordBot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private void onDeath(PlayerDeathEvent e){
        Player target = e.getEntity();
        target.setGameMode(org.bukkit.GameMode.SPECTATOR);
        DiscordBot.sendDeathMessage(e);
    }

}
