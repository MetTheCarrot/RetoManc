package carrot.mc.mancchallenge.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static carrot.mc.mancchallenge.Utils.Chat.color;

public class Death implements Listener {

    @EventHandler
    private void onDeath(PlayerDeathEvent e){
        Player target = e.getEntity();
        target.setGameMode(org.bukkit.GameMode.SPECTATOR);
        target.sendMessage(color("&cHas muerto definitavemente."));
    }

}
