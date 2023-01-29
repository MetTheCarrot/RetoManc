package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.Chat;
import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static carrot.mc.mancchallenge.Utils.Chat.broadCast;
import static carrot.mc.mancchallenge.Utils.Day.getDay;

public class TRetos {

    public static int cantidadDeItems(Player target, Material material){
        int total = 0;
        for(ItemStack item : target.getInventory().getContents()){
            if(item != null && item.getType() == material)
                total += item.getAmount();
        }
        return total;
    }

    public static boolean haveBowWithPowerX(Player target){
        for(ItemStack item : target.getInventory().getContents()){
            if(item == null || !item.getType().equals(Material.BOW)) continue;
            // power x
            if(item.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 10)
                return true;
        }
        return false;
    }

    public static int getDurability(ItemStack item){
        if(item == null) return 0;
        return item.getType().getMaxDurability() - item.getDurability();
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
                        for(ItemStack armor : target.getInventory().getArmorContents())
                            if(!isNetheritePiece(armor)) return;
                        PlayerData.completeReto(target, 9);
                    }
                    // Reto 14
                    if(day == 14){
                        if(PlayerData.isComplete(target, 14)) return;
                        ItemStack elytra = target.getInventory().getChestplate();
                        if(elytra == null || !elytra.getType().equals(Material.ELYTRA)) return;
                        if(getDurability(elytra) == 1){
                            target.playSound(target.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                            target.getInventory().setChestplate(null);
                            PlayerData.completeReto(target, 14);
                        }
                    }
                    // Reto 16
                    if(day == 16){
                        if(PlayerData.isComplete(target, 16)) return;
                        if(haveBowWithPowerX(target)){
                            PlayerData.completeReto(target, 16);

                        }
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
