package carrot.mc.mancchallenge;

import carrot.mc.mancchallenge.Commands.Public;
import carrot.mc.mancchallenge.Task.Pause;
import carrot.mc.mancchallenge.Utils.Day;
import org.bukkit.plugin.java.JavaPlugin;

import static carrot.mc.mancchallenge.Discord.DiscordBot.startBot;
import static carrot.mc.mancchallenge.Listeners.Register.registerListeners;
import static carrot.mc.mancchallenge.Task.Bossbar.*;
import static carrot.mc.mancchallenge.Task.Score.updateGlobalScoboard;
import static carrot.mc.mancchallenge.Task.TRetos.taskRetos;
import static carrot.mc.mancchallenge.Utils.Day.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin = this;
        Day.setPause(true);
        checkTime();
        registerListeners();
        updateGlobalScoboard();
        startBot();
        Pause.pauseTask();
        addAllPlayersToBar();
        taskDay();
        bossbarTask();
        taskRetos();
        getCommand("pause").setExecutor(new Public());
    }

    @Override
    public void onDisable(){
        Day.setPause(true);
        removeAllPlayersFromBar();
    }

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }


}
