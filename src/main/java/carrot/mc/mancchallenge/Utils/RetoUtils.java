package carrot.mc.mancchallenge.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import static carrot.mc.mancchallenge.Utils.Chat.broadCast;
import static carrot.mc.mancchallenge.Utils.Chat.color;

public class RetoUtils {

    public static boolean esReliquia(ItemStack stack) {
        if (stack == null) return false;
        if (!stack.hasItemMeta()) return false;
        return stack.getType() == Material.LIGHT_BLUE_DYE && stack.getItemMeta().getDisplayName().endsWith(color("&6Reliquia Del Fin"));
    }

    private static boolean isLeatherArmor(ItemStack armor){
        return armor.getType().equals(Material.LEATHER_HELMET) ||
                armor.getType().equals(Material.LEATHER_CHESTPLATE) ||
                armor.getType().equals(Material.LEATHER_LEGGINGS) ||
                armor.getType().equals(Material.LEATHER_BOOTS);
    }

    private static boolean isGoldenArmor(ItemStack armor){
        return armor.getType().equals(Material.GOLDEN_HELMET) ||
                armor.getType().equals(Material.GOLDEN_CHESTPLATE) ||
                armor.getType().equals(Material.GOLDEN_LEGGINGS) ||
                armor.getType().equals(Material.GOLDEN_BOOTS);
    }

    private static boolean isIronArmor(ItemStack armor){
        return armor.getType().equals(Material.IRON_HELMET) ||
                armor.getType().equals(Material.IRON_CHESTPLATE) ||
                armor.getType().equals(Material.IRON_LEGGINGS) ||
                armor.getType().equals(Material.IRON_BOOTS);
    }

    private static boolean isDiamondArmor(ItemStack armor){
        return armor.getType().equals(Material.DIAMOND_HELMET) ||
                armor.getType().equals(Material.DIAMOND_CHESTPLATE) ||
                armor.getType().equals(Material.DIAMOND_LEGGINGS) ||
                armor.getType().equals(Material.DIAMOND_BOOTS);
    }

    private static boolean isCotaDeMalla(ItemStack armor){
        return armor.getType().equals(Material.CHAINMAIL_HELMET) ||
                armor.getType().equals(Material.CHAINMAIL_CHESTPLATE) ||
                armor.getType().equals(Material.CHAINMAIL_LEGGINGS) ||
                armor.getType().equals(Material.CHAINMAIL_BOOTS);
    }

    public static boolean isUltraEsqueletoGuerrero(Entity e){
        if(e.getType() != EntityType.SKELETON) return false;
        for(ItemStack armor : ((Skeleton) e).getEquipment().getArmorContents()){
            if(!isDiamondArmor(armor)) return false;
        }
        return true;
    }

    public static boolean isUltraEsqueletoInfernal(Entity e){
        if(e.getType() != EntityType.SKELETON) return false;
        for(ItemStack armor : ((Skeleton) e).getEquipment().getArmorContents())
            if(!isIronArmor(armor)) return false;
        return true;
    }

    public static boolean isUltraEsqueletoAsesino(Entity e){
        if(!e.getType().equals(EntityType.SKELETON)) return false;
        if(e.getType() != EntityType.SKELETON) return false;
        for(ItemStack armor : ((Skeleton) e).getEquipment().getArmorContents())
            if(!isGoldenArmor(armor)) return false;
        return true;
    }

    public static boolean isUltraEsqueletoTactico(Entity e){
        if(e.getType() != EntityType.WITHER_SKELETON) return false;
        for(ItemStack armor : ((WitherSkeleton) e).getEquipment().getArmorContents())
            if(!isCotaDeMalla(armor)) return false;
        return true;
    }

    public static boolean isUltraEsqueletoPesadilla(Entity e){
        if(e.getType() != EntityType.WITHER_SKELETON) return false;
        for(ItemStack armor : ((WitherSkeleton) e).getEquipment().getArmorContents())
            if(!isLeatherArmor(armor)) return false;
        return true;
    }

    public static boolean killEveryClassDay17(Player target){
        // Si mato minimo 1 de cada clase, lo cumplio
        return PlayerData.getCountMobs(target, 17, "ultra_esqueleto_guerrero") >= 1 &&
                PlayerData.getCountMobs(target, 17, "ultra_esqueleto_infernal") >= 1 &&
                PlayerData.getCountMobs(target, 17, "ultra_esqueleto_asesino") >= 1 &&
                PlayerData.getCountMobs(target, 17, "ultra_esqueleto_tactico") >= 1 &&
                PlayerData.getCountMobs(target, 17, "ultra_esqueleto_pesadilla") >= 1;
    }

    public static void detectDia17(Player target, Entity entity){
        // Ultra Esqueleto Guerrero
        if(isUltraEsqueletoGuerrero(entity))
            PlayerData.setCountMobs(target, 17, "ultra_esqueleto_guerrero", 1);
        // Ultra Esqueleto Infernal
        if(isUltraEsqueletoInfernal(entity))
            PlayerData.setCountMobs(target, 17, "ultra_esqueleto_infernal", 1);
        // Ultra Esqueleto Asesino
        if(isUltraEsqueletoAsesino(entity))
            PlayerData.setCountMobs(target, 17, "ultra_esqueleto_asesino", 1);
        // Ultra Esqueleto Tactico
        if(isUltraEsqueletoTactico(entity))
            PlayerData.setCountMobs(target, 17, "ultra_esqueleto_tactico", 1);
        // Ultra Esqueleto Pesadilla
        if(isUltraEsqueletoPesadilla(entity))
            PlayerData.setCountMobs(target, 17, "ultra_esqueleto_pesadilla", 1);
        if(killEveryClassDay17(target))
            PlayerData.completeReto(target, 17);
    }

    private static boolean isEsqueletoGuerrero(Entity e){
        if(e.getType() != EntityType.SKELETON) return false;
        for(ItemStack armor : ((Skeleton) e).getEquipment().getArmorContents()){
            if(!isDiamondArmor(armor)) return false;
        }
        return true;
    }

    private static boolean isEsqueletoInfernal(Entity e){
        if(e.getType() != EntityType.SKELETON) return false;
        for(ItemStack armor : ((Skeleton) e).getEquipment().getArmorContents())
            if(!isIronArmor(armor)) return false;
        return true;
    }

    private static boolean isEsqueletoAsesino(Entity e){
        if(e.getType() != EntityType.SKELETON) return false;
        for(ItemStack armor : ((Skeleton) e).getEquipment().getArmorContents())
            if(!isGoldenArmor(armor)) return false;
        return true;
    }

    private static boolean isEsqueletoTactico(Entity e){
        if(e.getType() != EntityType.WITHER_SKELETON) return false;
        for(ItemStack armor : ((WitherSkeleton) e).getEquipment().getArmorContents())
            if(!isCotaDeMalla(armor)) return false;
        return true;
    }

    private static boolean isEsqueletoPesadilla(Entity e){
        if(e.getType() != EntityType.WITHER_SKELETON) return false;
        for(ItemStack armor : ((WitherSkeleton) e).getEquipment().getArmorContents())
            if(!isLeatherArmor(armor)) return false;
        return true;
    }

    public static boolean killEveryClassDay15(Player target){
        return PlayerData.getCountMobs(target, 15, "esqueleto_guerrero") >= 1 && PlayerData.getCountMobs(target, 15, "esqueleto_infernal") >= 1 && PlayerData.getCountMobs(target, 15, "esqueleto_asesino") >= 1 && PlayerData.getCountMobs(target, 15, "esqueleto_tactico") >= 1 && PlayerData.getCountMobs(target, 15, "esqueleto_pesadilla") >= 1;
    }

    public static void detectDia15(Player target, Entity entity){
        // Esqueleto Guerrero
        if(isEsqueletoGuerrero(entity))
            PlayerData.setCountMobs(target, 15, "esqueleto_guerrero", 1);
        // Esqueleto Infernal
        if(isEsqueletoInfernal(entity))
            PlayerData.setCountMobs(target, 15, "esqueleto_infernal", 1);
        // Esqueleto Asesino
        if(isEsqueletoAsesino(entity))
            PlayerData.setCountMobs(target, 15, "esqueleto_asesino", 1);
        // Esqueleto Táctico
        if(isEsqueletoTactico(entity))
            PlayerData.setCountMobs(target, 15, "esqueleto_tactico", 1);
        // Esqueleto Pesadilla
        if(isEsqueletoPesadilla(entity))
            PlayerData.setCountMobs(target, 15, "esqueleto_pesadilla", 1);
        // Si mato minimo cada 1 de cada clase, completo el reto
        if(killEveryClassDay15(target))
            PlayerData.completeReto(target, 15);
    }

    public static boolean haveName(Entity entity, String name) {
        Chat.broadCast("Entity name: " + entity.getCustomName());
        return entity.getCustomName() != null && color(entity.getCustomName()).equals(color(name));
    }

    public static boolean isEnderCreeper(Entity e){
        if(e.getType() != EntityType.CREEPER) return false;
        return haveName(e, "&6Ender Creeper");
    }

    public static boolean isEnderQuantumCreeper(Entity e){
        if(e.getType() != EntityType.CREEPER) return false;
        return haveName(e, "&6Ender Quantum Creeper");
    }

    public static boolean isSword(ItemStack stack){
        if(stack == null) return false;
        return stack.getType().name().endsWith("_SWORD");
    }

    public static String getLocationString(Location location) {
        String x = (String.valueOf(location.getBlockX()));
        String y = (String.valueOf(location.getBlockY()));
        String z = (String.valueOf(location.getBlockZ()));
        return "X: " + x + " Y: " + y + " Z: " + z;
    }

    public static void regen1Cora(Player target){
        double health = target.getHealth();
        double maxHealth = target.getMaxHealth();
        if(health < maxHealth) target.setHealth(health + 1);
        if(maxHealth - health < 1) target.setHealth(maxHealth); // Si la diferencia entre la vida y la vida máxima es menor a 1, se le da la vida máxima
    }

    public static String getWorld(Player target){
        String world = target.getWorld().getName();
        switch (world) {
            case "world":
                return "Overworld";
            case "world_nether":
                return "Nether";
            case "world_the_end":
                return "End";
            case "pdc_the_beginning":
                return "The Beginning";
            default:
                return "Desconocido";
        }
    }

    public static String getCustomCause(EntityDamageEvent e) {
        String reasonDeath = "Desconocido";
        switch (e.getCause()) {
            case FIRE :
            case FIRE_TICK:
                reasonDeath = "Fuego";
                break;
            case LAVA:
                reasonDeath = "Lava";
                break;
            case DROWNING :
                reasonDeath = "Ahogamiento";
                break;
            case BLOCK_EXPLOSION :
                reasonDeath = "Explosion";
                break;
            case VOID :
                reasonDeath = "Vacío";
                break;
            case LIGHTNING :
                reasonDeath = "Rayo";
                break;
            case POISON :
                reasonDeath = "Veneno";
                break;
            case MAGIC :
                reasonDeath = "Magia";
                break;
            case WITHER :
                reasonDeath = "Wither";
                break;
            case THORNS :
                reasonDeath = "Espinas";
                break;
            case DRAGON_BREATH :
                reasonDeath = "Aliento de dragón";
                break;
            case CONTACT :
                reasonDeath = "Contacto";
                break;
            case CRAMMING :
                reasonDeath = "Entity Cramming";
                break;
            case HOT_FLOOR :
                reasonDeath = "Piso en llamas";
                break;
            case DRYOUT :
                reasonDeath = "Secado";
                break;
            case STARVATION :
                reasonDeath = "Hambre";
                break;
            case SUFFOCATION :
                reasonDeath = "Asfixia";
                break;
            case FLY_INTO_WALL :
                reasonDeath = "Colisión";
            case ENTITY_ATTACK :
            case ENTITY_SWEEP_ATTACK :
            {
                if (e instanceof EntityDamageByEntityEvent) {
                    reasonDeath =  "Entidad [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]";
                }
                break;
            }
            case PROJECTILE :
            {
                if (e instanceof EntityDamageByEntityEvent) {
                    Projectile proj = (Projectile) ((EntityDamageByEntityEvent)e).getDamager();
                    if (proj.getShooter() != null) {
                        Entity shooter = (Entity) proj.getShooter();
                        reasonDeath =  "Proyectil [" + shooter.getName() + "]";
                    } else {
                        reasonDeath =  "Proyectil [" + ((EntityDamageByEntityEvent)e).getDamager().getName() + "]";
                    }
                }
                break;
            }
            case ENTITY_EXPLOSION :
            {
                if (e instanceof EntityDamageByEntityEvent){
                    if (((EntityDamageByEntityEvent)e).getDamager() instanceof Fireball && ((Fireball) ((EntityDamageByEntityEvent)e).getDamager()).getShooter() != null) {
                        Fireball fireball = (Fireball) ((EntityDamageByEntityEvent)e).getDamager();
                        LivingEntity et = (LivingEntity) fireball.getShooter();
                        reasonDeath =  "Explosión [" + et.getName() + "]";
                    } else {
                        reasonDeath =  "Explosión [" + ((EntityDamageByEntityEvent)e).getDamager().getName() + "]";
                    }
                }
                break;
            }
            case SUICIDE :
                reasonDeath = "Suicidio";
                break;
            default :
                reasonDeath = "Desconocido";
        } return reasonDeath;
    }

}
