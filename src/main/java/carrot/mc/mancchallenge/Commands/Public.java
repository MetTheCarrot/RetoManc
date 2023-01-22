package carrot.mc.mancchallenge.Commands;

import carrot.mc.mancchallenge.Utils.Day;
import carrot.mc.mancchallenge.Utils.PlayerData;
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
        if(args.length == 0){
            Day.setPause(!Day.isPause());
            target.sendMessage(color("&aEl juego ha sido " + (Day.isPause() ? "pausado" : "reanudado") + "!"));
        }
        if(args.length > 0){
            // cambiar numero de tótems usados
            if(args[0].equalsIgnoreCase("ewfby8winojsmkpmasiygg1")){
                if(args.length >= 2){
                    try{
                        int newTotem = Integer.parseInt(args[1]);
                        PlayerData.setTotem(target, newTotem);
                        target.sendMessage(color("&aEl totem actual es: &e" + PlayerData.getUseTotem(target)));
                    }catch (NumberFormatException e){
                        target.sendMessage(color("&cEl argumento debe ser un numero!"));
                    }
                }
            }
            // cambiar numero de dia
            if(args[0].equalsIgnoreCase("ewfby8winojsmkpmasiygg2")){
                if(args.length >= 2){
                    try{
                        int newDay = Integer.parseInt(args[1]);
                        Day.setTotalTimeGlobalPlayedTime(newDay);
                        target.sendMessage(color("&aEl dia actual es: &e" + Day.getDay()));
                    }catch (NumberFormatException e){
                        target.sendMessage(color("&cEl argumento debe ser un numero!"));
                    }
                }
            }
            // cambiar estado de un reto
            if(args[0].equalsIgnoreCase("ewfby8winojsmkpmasiygg3")){
                if(args.length >= 2){
                    try{
                        int reto = Integer.parseInt(args[1]);
                        boolean toggle = Boolean.parseBoolean(args[2]);
                        PlayerData.toggleReto(target, reto, toggle);
                        target.sendMessage(color("&aEl reto &e" + reto + " &aahora esta " + (toggle ? "activado" : "desactivado") + "!"));
                    }catch (NumberFormatException e){
                        target.sendMessage(color("&cEl argumento debe ser un numero!"));
                    }
                }
            }
        }
        return true;
    }

}
