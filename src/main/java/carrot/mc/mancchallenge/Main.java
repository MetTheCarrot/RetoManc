package carrot.mc.mancchallenge;

import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.java.JavaPlugin;

import static carrot.mc.mancchallenge.Listeners.Register.registerListeners;
import static carrot.mc.mancchallenge.Task.Score.updateGlobalScoboard;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin = this;
        registerListeners();
        updateGlobalScoboard();
    }

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

}
