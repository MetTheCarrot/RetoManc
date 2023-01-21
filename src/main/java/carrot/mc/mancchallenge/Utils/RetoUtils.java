package carrot.mc.mancchallenge.Utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RetoUtils {

    public static String getLocationString(Location location) {
        String x = (String.valueOf(location.getBlockX()));
        String y = (String.valueOf(location.getBlockY()));
        String z = (String.valueOf(location.getBlockZ()));
        return "X: " + x + " Y: " + y + " Z: " + z;
    }

    public static void killAll(){
        for(Player target : Bukkit.getOnlinePlayers()){
            if(!target.getGameMode().equals(GameMode.SPECTATOR))
                target.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 254));
        }
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
