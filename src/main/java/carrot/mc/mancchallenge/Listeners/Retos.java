package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.Chat;
import carrot.mc.mancchallenge.Utils.PlayerData;
import carrot.mc.mancchallenge.Utils.RetoUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.*;

import static carrot.mc.mancchallenge.Utils.Chat.color;
import static carrot.mc.mancchallenge.Utils.Day.getDay;
import static carrot.mc.mancchallenge.Utils.Day.isPause;
import static carrot.mc.mancchallenge.Utils.PlayerData.getCountMobs;
import static carrot.mc.mancchallenge.Utils.PlayerData.setCountMobs;
import static carrot.mc.mancchallenge.Utils.RetoUtils.*;

public class Retos implements Listener {

    // pausas

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void whenDamage(EntityDamageEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void whenDamagePlayer2(EntityDamageByEntityEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void pauseEvent3(BlockBreakEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void pauseEvent4(PlayerInteractEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void pauseEvent5(InventoryOpenEvent e){
        if(isPause()) e.getPlayer().closeInventory();
    }

    // retos

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void craftItem(CraftItemEvent e){
        Player target = (Player) e.getWhoClicked();
        ItemStack item = e.getRecipe().getResult();
        int day = getDay();
        // Reto 1
        if(day == 1 && item.getType().equals(Material.CAKE)) PlayerData.completeReto(target, 1);
        // Restricción reto 13
        if(day == 13){
            if(esReliquia(item)){
                e.setCancelled(true);
                target.sendMessage(color("&cNo puedes craftear las reliquias hasta el día 14!"));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void craftPotion(BrewEvent e){

        //Players
        ArrayList<Player> target = new ArrayList<>();
        e.getBlock().getWorld().getNearbyEntities(e.getBlock().getLocation(), 20, 20, 20).forEach(entity -> {
            if(entity instanceof Player) target.add((Player) entity);
        });

        //Items
        List<ItemStack> item = Arrays.asList(e.getContents().getItem(0), e.getContents().getItem(1), e.getContents().getItem(2));

        //Check potions
        int day = getDay();

        for(Player p : target)
            for(ItemStack i : item){
                if(i == null || !i.hasItemMeta()) continue;
                PotionMeta meta = (PotionMeta) i.getItemMeta();
                PotionType type = meta.getBasePotionData().getType();
                // Reto 2
                if(day == 2 && type.equals(PotionType.WATER_BREATHING))
                    PlayerData.completeReto(p, 2);
                // Reto 6
                if(day == 6 && type.equals(PotionType.NIGHT_VISION))
                    PlayerData.completeReto(p, 6);
                // Reto 7
                if(day == 7 && type.equals(PotionType.TURTLE_MASTER))
                    PlayerData.completeReto(p, 7);
            }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void summonGolem(CreatureSpawnEvent e){
        EntityType type = e.getEntityType();
        if(!type.equals(EntityType.IRON_GOLEM)) return;
        int day = getDay();
        Location summonGolem = e.getLocation();
        for(Player target : e.getEntity().getWorld().getNearbyEntities(summonGolem, 10, 10, 10).stream().filter(entity -> entity instanceof Player).toArray(Player[]::new)){
            // Reto 8
            if(day == 8) PlayerData.completeReto(target, 8);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void killMobs(EntityDeathEvent e){
        if(e.getEntity().getKiller() == null) return;
        Player target = e.getEntity().getKiller();
        Entity enemigo = e.getEntity();
        EntityType mob = e.getEntityType();
        int day = getDay();
        // Reto 4
        if(day == 4 && mob.equals(EntityType.SPIDER)){
            setCountMobs(target, day, mob.name(), 1);
            if(getCountMobs(target, day, mob.name()) == 50) PlayerData.completeReto(target, 4);
        }
        // Reto 5
        if(day == 5 && mob.equals(EntityType.CAVE_SPIDER)){
            setCountMobs(target, day, mob.name(), 1);
            if(getCountMobs(target, day, mob.name()) == 15) PlayerData.completeReto(target, 5);
        }
        // Reto 10
        if(day == 10 && mob.equals(EntityType.ENDER_DRAGON)){
            for(Player players: Bukkit.getOnlinePlayers()){
                PlayerData.completeReto(players, 10);
            }
        }
        // Reto 12+1
        if(day == 13 && mob.equals(EntityType.CREEPER)){
            if(isEnderCreeper(enemigo)) PlayerData.setCountMobs(target, day, "ender-creeper", 1);
            if(getCountMobs(target, day, "ender-creeper") == 20) PlayerData.completeReto(target, 13);
        }
        // Reto 15
        if(day == 15)
            RetoUtils.detectDia15(target, enemigo);
        // Reto 17
        if(day == 17)
            RetoUtils.detectDia17(target, enemigo);
        // Reto 19
        if(day == 19 && mob.equals(EntityType.WITHER)){
            Location witherLocation = e.getEntity().getLocation();
            for(Player players: e.getEntity().getWorld().getNearbyEntities(witherLocation, 100, 100, 100).stream().filter(entity -> entity instanceof Player).toArray(Player[]::new)){
                PlayerData.completeReto(players, 19);
            }
        }
        // Reto 20
        if (day == 20 && mob.equals(EntityType.CREEPER)) {
            ItemStack itemPlayer = target.getInventory().getItemInMainHand();
            if(RetoUtils.isSword(itemPlayer) && isEnderQuantumCreeper(enemigo))
                PlayerData.completeReto(target, 20);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void changeDimension(PlayerChangedWorldEvent e){
        Player target = e.getPlayer();
        int day = getDay();
        // Reto 18
        if(day == 18){
            String worldName = target.getWorld().getName();
            if(worldName.equalsIgnoreCase("pdc_the_beginning"))
                PlayerData.completeReto(target, 18);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void finishRaid(RaidFinishEvent e){
        int levelRaid = e.getRaid().getBadOmenLevel();
        for(Player target : e.getWinners())
            // Reto 16
            if(getDay() == 16 && levelRaid == 5) PlayerData.completeReto(target, 16);
    }

    public static boolean isShulkerBox(ItemStack item){
        // si el nombre termina con "BOX" es un shulker box
        return (item.getType().name().endsWith("BOX"));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private static void dropItem(PlayerDropItemEvent e){
        Player target = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        target.sendMessage("dropeaste un item" + item.getType().name());
        int day = getDay();
        if(day == 12){
            if(isShulkerBox(item)){
                e.getItemDrop().remove();
                PlayerData.setDropItem(target, 12, "SHULKER_BOX", 1);
                if(PlayerData.getDropItem(target, 12, "SHULKER_BOX") == 2) PlayerData.completeReto(target, 12);
            }
        }
    }

}
