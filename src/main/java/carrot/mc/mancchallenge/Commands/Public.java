package carrot.mc.mancchallenge.Commands;

import carrot.mc.mancchallenge.Utils.Day;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static carrot.mc.mancchallenge.Utils.Chat.color;

public class Public implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)) return false;
        Player target = (Player) sender;
        Day.setPause(!Day.isPause());
        target.sendMessage(color("&aEl juego ha sido " + (Day.isPause() ? "pausado" : "reanudado") + "!"));
        return true;
    }

}
