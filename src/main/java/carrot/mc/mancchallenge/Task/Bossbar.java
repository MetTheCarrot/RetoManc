package carrot.mc.mancchallenge.Task;

import carrot.mc.mancchallenge.Main;
import carrot.mc.mancchallenge.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static carrot.mc.mancchallenge.Task.TRetos.isNetheritePiece;
import static carrot.mc.mancchallenge.Utils.Chat.broadCast;
import static carrot.mc.mancchallenge.Utils.Chat.color;
import static carrot.mc.mancchallenge.Utils.Day.*;
import static carrot.mc.mancchallenge.Utils.PlayerData.getCountMobs;
import static carrot.mc.mancchallenge.Utils.PlayerData.isComplete;

public class Bossbar {

    private static String getRetoProgress(Player target) {
        int progress = 0;
        switch (getDay()) {
            case 1: {
                if (isComplete(target, 1)) progress++;
                return "(" + progress + "/1)";
            }
            case 2: {
                if (isComplete(target, 2)) progress++;
                return "(" + progress + "/1)";
            }
            case 3: {
                if (isComplete(target, 3)) progress = 64;
                else progress = TRetos.cantidadDeItems(target, Material.OBSIDIAN);
                return "(" + progress + "/64)";
            }
            case 4: {
                if (isComplete(target, 4)) progress = 50;
                else progress = PlayerData.getCountMobs(target, 4, EntityType.SPIDER.name());
                return "(" + progress + "/50)";
            }
            case 5: {
                if (isComplete(target, 5)) progress = 15;
                else progress = PlayerData.getCountMobs(target, 5, EntityType.CAVE_SPIDER.name());
                return "(" + progress + "/15)";
            }
            case 6: {
                if (isComplete(target, 6)) progress++;
                return "(" + progress + "/1)";
            }
            case 7: {
                if (isComplete(target, 7)) progress++;
                return "(" + progress + "/1)";
            }
            case 8: {
                if (isComplete(target, 8)) progress++;
                return "(" + progress + "/1)";
            }
            case 9: {
                if (isComplete(target, 9)) progress = 4;
                else
                    for (ItemStack armor : target.getInventory().getArmorContents())
                        if (isNetheritePiece(armor)) progress++;
                return "(" + progress + "/4)";
            }
            case 10: {
                if (isComplete(target, 10)) progress++;
                return "(" + progress + "/1)";
            }
            case 11: {
                if (isComplete(target, 11)) progress++;
                return "(" + progress + "/1)";
            }
            case 12: {
                if (isComplete(target, 12)) progress = 2;
                else progress = PlayerData.getDropItem(target, 12, "SHULKER_BOX");
                return "(" + progress + "/2)";
            }
            case 13: {
                if (isComplete(target, 13)) progress = 20;
                else progress = PlayerData.getCountMobs(target, 13, "ender-creeper");
                return "(" + progress + "/20)";
            }
            case 14: {
                if (isComplete(target, 14)) progress++;
                return "(" + progress + "/1)";
            }
            case 15: {
                if (isComplete(target, 15)) progress = 5;
                else{
                    if(getCountMobs(target,15, "esqueleto_guerrero") > 0)
                        progress ++;
                    if(getCountMobs(target,15, "esqueleto_infernal") > 0)
                        progress ++;
                    if(getCountMobs(target,15, "esqueleto_asesino") > 0)
                        progress ++;
                    if(getCountMobs(target,15, "esqueleto_tactico") > 0)
                        progress ++;
                    if(getCountMobs(target,15, "esqueleto_pesadilla") > 0)
                        progress ++;
                }
                return "(" + progress + "/5)";
            }
            case 16: {
                if (isComplete(target, 16)) progress++;
                return "(" + progress + "/1)";
            }
            case 17: {
                if (isComplete(target, 17)) progress = 5;
                else {
                    if(getCountMobs(target,17, "ultra_esqueleto_guerrero") > 0)
                        progress ++;
                    if(getCountMobs(target,17, "ultra_esqueleto_infernal") > 0)
                        progress ++;
                    if(getCountMobs(target,17, "ultra_esqueleto_asesino") > 0)
                        progress ++;
                    if(getCountMobs(target,17, "ultra_esqueleto_tactico") > 0)
                        progress ++;
                    if(getCountMobs(target,17, "ultra_esqueleto_pesadilla") > 0)
                        progress ++;
                }
                return "(" + progress + "/5)";
            }
            case 18: {
                if (isComplete(target, 18)) progress++;
                return "(" + progress + "/1)";
            }
            case 19: {
                if (isComplete(target, 19)) progress++;
                return "(" + progress + "/1)";
            }
            case 20: {
                if (isComplete(target, 20)) progress++;
                return "(" + progress + "/1)";
            }
            default:
                return "";
        }
    }

    public static String getReto(int day, Player target){
        switch(day){
            case 1:
                return "Crea una tarta";
            case 2:
                return "Consigue una poción de respiración";
            case 3:
                return "Consigue 1 stack de obsidiana";
            case 4:
                return "Mata 50 arañas";
            case 5:
                return "Mata 15 arañas de cueva";
            case 6:
                return "Realiza una poción de visión nocturna";
            case 7:
                return "Realiza una poción de maestro tortuga con mas duración";
            case 8:
                return "Invoca un gólem de hierro";
            case 9:
                return "Consigue la armadura de Netherite";
            case 10:
            {
                if(isComplete(target, 10)) return "Mata al &c&mPermadeath Demon";
                else return "Mata al &cPermadeath Demon";
            }
            case 11:
                return "Consigue elytras";
            case 12:
                return "Consigue 2 shulkers y quémalos";
            case 13:
                return "No craftear la reliquia del fin (Hasta el día 14) y matar 20 EnderCreepers";
            case 14:
                return "Romper una elytra";
            case 15:
                return "Mata todo los tipos de esqueletos";
            case 16:
                return "Consigue el arco con power X";
            case 17:
                return "Mata a cada uno de los tipos de esqueletos";
            case 18:
                return "Entra al The Beginning";
            case 19:
                return "Mata un Wither Boss";
            case 20:
                return "Mata un Ender Quantum Creeper con una espada";
            default:
                return "Sobrevive";
        }
    }

    public static BarColor getColor(){
        int day = getDay();
        double retos = 5 / 21.0; // 5 colores para 21 retos
        int progreso = (int) Math.floor(retos * day);
        BarColor colorElegido = BarColor.GREEN; // 0 - 1
        if(progreso == 2) return BarColor.YELLOW;
        else if (progreso == 3) return BarColor.PINK;
        else if (progreso == 4) return BarColor.PURPLE;
        else if (progreso >= 5) return BarColor.RED;
        return colorElegido;
    }

    private static final NamespacedKey bossbar_key = BossbarBuilder.setKey("mancchallenge_bossbar");

    public static double getProgress(){
        try{
            int tiempoProximoDia = Integer.parseInt(diferenciaParaLlegarAlOtroDia());
            int days = getDay();
            return ((1.0/86400) * tiempoProximoDia) + 1;
        } catch(Exception e){
            return 0;
        }
    }

    public static String getTitle(Player target){
        return Chat.formatColor() + "Día " + getDay() + " &7- &f" + (isComplete(target,getDay()) ? "&m" : "") + getReto(getDay(), target) + " " + getRetoProgress(target) + "&r &7- &f" + Timestamp.TimestampToHour(Day.diferenciaParaLlegarAlOtroDia());
    }

    public static BossBar getBar(Player target){
        return new BossbarBuilder(bossbar_key)
                .setTitle(getTitle(target))
                .setColor(getColor())
                .setStyle(BarStyle.SOLID)
                .build();
    }

    public static BossBar getSingleBar(Player target){
        return target.getServer().getBossBar(bossbar_key);
    }

    public static void addPlayerToBar(Player target){
        try{
            getSingleBar(target).removePlayer(target);
        } catch(Exception e){
            getBar(target).addPlayer(target);
        }
        getBar(target).addPlayer(target);
    }

    public static void removePlayerFromBar(Player target){
        getSingleBar(target).removePlayer(target);
        getBar(target).removePlayer(target);
    }

    public static void removeAllPlayersFromBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            removePlayerFromBar(player);
        }
    }

    public static void addAllPlayersToBar(){
        for(Player player : Bukkit.getOnlinePlayers())
            addPlayerToBar(player);
    }

    public static void updateBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            BossBar bar = getSingleBar(player);
            if(bar == null) addPlayerToBar(player);
            bar.setTitle(color(getTitle(player)));
            bar.setColor(getColor());
            bar.setProgress(getProgress());
        }
    }

    public static void bossbarTask(){
        new BukkitRunnable(){
            @Override
            public void run() {
                updateBar();
            }
        }.runTaskTimer(Main.getPlugin(), 0, 20L);
    }

}
