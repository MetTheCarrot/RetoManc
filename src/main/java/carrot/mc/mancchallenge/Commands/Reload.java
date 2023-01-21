package carrot.mc.mancchallenge.Commands;

import carrot.mc.mancchallenge.Utils.Day;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)) return false;
        Player target = (Player) sender;
        target.sendMessage(args);
        Day.debug(Integer.parseInt(args[0]));
        return true;
    }
}
