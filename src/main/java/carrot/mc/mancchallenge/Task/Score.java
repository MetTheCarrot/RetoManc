package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.Chat;
import carrot.mc.mancchallenge.Utils.Day;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static carrot.mc.mancchallenge.Utils.Chat.color;
import static carrot.mc.mancchallenge.Utils.Chat.formatColor;
import static carrot.mc.mancchallenge.Utils.PlayerData.*;

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

    public static List<String> lineScore(String... score)
    {
        List<String> loreList = new ArrayList<>();
        for (String s : score)
        {
            loreList.add(color(s));
        }
        return loreList;
    }

    public static void updateScoreboard(FastBoard board, Player target){
        new BukkitRunnable(){
            @Override
            public void run() {
                board.updateLines(
                        lineScore(
                                " ",
                                "&7Jugador: &f" + target.getName(),
                                "&7Día: " + formatColor() + Day.getDay() + (Day.isPause() ? " &7(Pausado)" : ""),
                                "&7Tiempo: &f" + Day.formatTiempoDeJuego(),
                                "&7Totems: &f" + getUseTotem(target),
                                "&7Notch: &f" + getNotchUse(target),
                                "&7Daño: &f" + getDamage(target),
                                " "
                        )
                );
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

}
