package carrot.mc.mancchallenge.Utils;

import carrot.mc.mancchallenge.Data.PersistentData;
import carrot.mc.mancchallenge.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static carrot.mc.mancchallenge.Data.PersistentData.INT;

public class PlayerData {

    public static int getNotchUse(Player target){
        if(PersistentData.has(target, "notchUse", INT)) return PersistentData.get(target, "notchUse", INT);
        else return 0;
    }

    public static void setNotchUse(Player target, int value){
        PersistentData.set(target, "notchUse", INT, value);
    }

    public static int getUseTotem(Player target){
        if(PersistentData.has(target, "totem", INT)) return PersistentData.get(target, "totem", INT);
        else return 0;
    }

    public static int getDamage(Player target){
        if(PersistentData.has(target, "damage", INT)) return PersistentData.get(target, "damage", INT);
        else return 0;
    }

    public static void addDamage(Player target, int damage){
        PersistentData.set(target, "damage", INT, getDamage(target) + damage);
    }

    public static void setTotem(Player target, int value){
        PersistentData.set(target, "totem", INT, value);
    }

    public static int getRegean(Player target){
        if(PersistentData.has(target, "regean", INT)) return PersistentData.get(target, "regean", INT);
        else return 0;
    }

    public static boolean canRegean(Player target){
        return getRegean(target) > 0;
    }

    public static void setRegean(Player target, int seconds){
        PersistentData.set(target, "regean", INT, seconds);
        new BukkitRunnable(){
            @Override
            public void run() {
                if(canRegean(target))
                    setRegean(target, getRegean(target) - 1);
                else cancel();
            }
        }.runTaskLater(Main.getPlugin(), 20L);
    }

}
