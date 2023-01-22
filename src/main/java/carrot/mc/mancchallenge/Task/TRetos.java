package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static carrot.mc.mancchallenge.Utils.Day.getDay;

public class TRetos {

    private static int cantidadDeItems(Player target, Material material){
        int total = 0;
        for(ItemStack item : target.getInventory().getContents()){
            if(item != null && item.getType() == material)
                total += item.getAmount();
        }
        return total;
    }

    public static void taskRetos() {
        int day = getDay();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    // Reto 3
                    if(day == 3 && cantidadDeItems(target, Material.OBSIDIAN) >= 64)
                        PlayerData.completeReto(target, 3);
                    // Reto 11
                    if(day == 11 && cantidadDeItems(target, Material.ELYTRA ) >= 1)
                        PlayerData.completeReto(target, 11);
                    // Reto 9
                    if(day == 9) {
                        ItemStack[] armor = target.getInventory().getArmorContents();
                        if(isNetheritePiece(armor[0]) && isNetheritePiece(armor[1]) && isNetheritePiece(armor[2]) && isNetheritePiece(armor[3]))
                            PlayerData.completeReto(target, 9);
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(), 0, 20L);
    }

    public static boolean isNetheritePiece(ItemStack s) {
        if (s == null) return false;
        if (s.hasItemMeta())
            return s.getItemMeta().isUnbreakable() && ChatColor.stripColor(s.getItemMeta().getDisplayName()).startsWith("Netherite");
        return false;
    }
}
