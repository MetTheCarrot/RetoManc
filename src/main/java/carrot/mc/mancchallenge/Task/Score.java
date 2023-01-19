package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.Chat;
import carrot.mc.mancchallenge.Utils.PlayerData;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static carrot.mc.mancchallenge.Utils.Chat.formatTimePlayed;
import static carrot.mc.mancchallenge.Utils.PlayerData.getUseTotem;

public class Score {

    public static void updateGlobalScoboard(){
        for(Player target : Bukkit.getOnlinePlayers())
            createScoreboard(target);
    }

    public static void createScoreboard(Player target){
        FastBoard board = new FastBoard(target);
        board.updateTitle(Chat.getPrefix());
        updateScoreboard(board, target);
    }

    public static void updateScoreboard(FastBoard board, Player target){
        new BukkitRunnable(){
            @Override
            public void run() {
                board.updateLines(
                        " ",
                        "§7Jugador: §f" + target.getName(),
                        "§7Tiempo: §f" + formatTimePlayed(target),
                        "§7Totems: §f" + getUseTotem(target),
                        "§7Daño: §f" + PlayerData.getDamage(target),
                        " "
                );
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

}
