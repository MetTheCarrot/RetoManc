package carrot.mc.mancchallenge;

import carrot.mc.mancchallenge.Commands.BossBattle;
import carrot.mc.mancchallenge.Commands.Pause;
import carrot.mc.mancchallenge.Utils.Day;
import org.bukkit.plugin.java.JavaPlugin;

import static carrot.mc.mancchallenge.Discord.DiscordBot.startBot;
import static carrot.mc.mancchallenge.Listeners.Register.registerListeners;
import static carrot.mc.mancchallenge.Task.Bossbar.*;
import static carrot.mc.mancchallenge.Task.Pause.pauseTask;
import static carrot.mc.mancchallenge.Task.Score.updateGlobalScoboard;
import static carrot.mc.mancchallenge.Task.TRetos.taskRetos;
import static carrot.mc.mancchallenge.Utils.Day.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin = this;
        startBot();
        Day.setPause(true);
        registerListeners();
        updateGlobalScoboard();
        pauseTask();
        addAllPlayersToBar();
        taskDay();
        bossbarTask();
        taskRetos();
        getCommand("pause").setExecutor(new Pause());
        getCommand("bossbattle").setExecutor(new BossBattle());
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

    public static void disablePlugin(){
        Main.getPlugin().getPluginLoader().disablePlugin(Main.getPlugin());
        Main.getPlugin().getServer().getScheduler().cancelTasks(Main.getPlugin());
        Main.getPlugin().getServer().getPluginManager().disablePlugin(Main.getPlugin());
        plugin = null;
    }


}
