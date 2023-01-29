package carrot.mc.mancchallenge.Commands;

import carrot.mc.mancchallenge.Utils.Day;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BossBattle implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if (!(sender instanceof Player)) return false;
        Day.toggleBossBattleMode();
        return true;
    }

}
