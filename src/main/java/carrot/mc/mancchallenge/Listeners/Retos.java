package carrot.mc.mancchallenge.Listeners;

import carrot.mc.mancchallenge.Utils.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.*;

import static carrot.mc.mancchallenge.Utils.Chat.broadCast;
import static carrot.mc.mancchallenge.Utils.Chat.color;
import static carrot.mc.mancchallenge.Utils.Day.getDay;
import static carrot.mc.mancchallenge.Utils.Day.isPause;
import static carrot.mc.mancchallenge.Utils.PlayerData.getCountMobs;
import static carrot.mc.mancchallenge.Utils.PlayerData.setCountMobs;

public class Retos implements Listener {

    // pausas

    @EventHandler
    public void whenDamage(EntityDamageEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler
    public void whenDamagePlayer2(EntityDamageByEntityEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler
    public void pauseEvent3(BlockBreakEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler
    public void pauseEvent4(PlayerInteractEvent e) {
        if (isPause()) e.setCancelled(true);
    }

    @EventHandler
    public void pauseEvent5(InventoryOpenEvent e){
        if(isPause()) e.getPlayer().closeInventory();
    }

    // retos

    @EventHandler
    private static void craftItem(CraftItemEvent e){
        Player target = (Player) e.getWhoClicked();
        ItemStack item = e.getRecipe().getResult();
        int day = getDay();
        // Reto 1
        if(day == 1 && item.getType().equals(Material.CAKE)) PlayerData.completeReto(target, 1);
    }

    @EventHandler
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

    @EventHandler
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

    @EventHandler
    private static void killMobs(EntityDeathEvent e){
        if(e.getEntity().getKiller() == null) return;
        Player target = e.getEntity().getKiller();
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
            Location enderDragon = e.getEntity().getLocation();
            for(Player p : e.getEntity().getWorld().getNearbyEntities(enderDragon, 500, 500, 500).stream().filter(entity -> entity instanceof Player).toArray(Player[]::new)){
                PlayerData.completeReto(p, 10);
            }
        }
    }

    @EventHandler
    private static void romperItem(PlayerItemBreakEvent e){
        Player target = e.getPlayer();
        int day = getDay();
        // Reto 14
        if(day == 14){
            ItemStack item = e.getBrokenItem();
            if(item.getType().equals(Material.ELYTRA)) PlayerData.completeReto(target, 14);
        }
    }

    @EventHandler
    private static void finishRaid(RaidFinishEvent e){
        int levelRaid = e.getRaid().getBadOmenLevel();
        for(Player target : e.getWinners())
            // Reto 16
            if(getDay() == 16 && levelRaid == 5) PlayerData.completeReto(target, 16);
    }

}
