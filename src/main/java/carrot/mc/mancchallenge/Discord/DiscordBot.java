package carrot.mc.mancchallenge.Discord;

import carrot.mc.mancchallenge.Task.Bossbar;
import carrot.mc.mancchallenge.Utils.Day;
import carrot.mc.mancchallenge.Utils.RetoUtils;
import carrot.mc.mancchallenge.Utils.Timestamp;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static carrot.mc.mancchallenge.Utils.Day.getDay;
import static carrot.mc.mancchallenge.Utils.PlayerData.*;

import java.awt.*;
import java.util.Objects;

import static carrot.mc.mancchallenge.Utils.PlayerData.getDamage;
import static carrot.mc.mancchallenge.Utils.RetoUtils.getCustomCause;

import carrot.dev.CarrotData;

public class DiscordBot {

    private static TextChannel deathChannelNotify;
    private static TextChannel completeRetosChannelNotify;
    private static TextChannel totemChannelNotify;

    public static void startBot(){
        try{
            String token = CarrotData.creisteQueSeriaPublicoElTokenFacilmenteJaja;
            String deathChannelID = "1066030685309128714";
            String totemChatChannelId = "1066402371661467728";
            String retosChatChannelID = "1066402344620806256";
            JDA jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.watching("..."))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build()
                    .awaitReady();
            deathChannelNotify = jda.getTextChannelById(deathChannelID);
            totemChannelNotify = jda.getTextChannelById(totemChatChannelId);
            completeRetosChannelNotify = jda.getTextChannelById(retosChatChannelID);
        } catch (Exception e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("Error al iniciar el bot de discord, el token es invalido.");
        }
    }

    private static void setInfo(EmbedBuilder embed, Player target){
        // Información de muerte

        embed.addField(":compass: Localización", RetoUtils.getLocationString(target.getLocation()), true);
        embed.addField("\uD83D\uDDFA️ Mundo", RetoUtils.getWorld(target), true);
        embed.addField(":star: Tiempo jugado: ", Timestamp.TimestampToHour(String.valueOf(Math.round(target.getStatistic(org.bukkit.Statistic.PLAY_ONE_MINUTE) / 20))), true);

        // Informacion del jugador

        embed.addField("<:corazon:1066394262830321735> Daño recibido: ", getDamage(target) + " corazones", true);
        embed.addField("<:totem:1066391451568721920> Totems usados: ", getUseTotem(target) + " ocasiones", true);
        embed.addField("<:notch:1066392992526647376> Notch usadas: ", getNotchUse(target) + " ocasiones", true);

        // Información del juego

        embed.addField(":clock10: Tiempo de juego: " + Day.formatTotalTimeGlobalPlayedTime() , ":scroll: Tiempo total: " + Day.getStartTime(), true);
        embed.addField(":shield: Tiempo de pausa: " + Day.formatTotalTimeGlobalPauseTime(), ":book: Total pausas: " + Day.getTotalPauses(), true);

    }

    public static void sendTotemMessage(EntityResurrectEvent e){
        Player target = (Player) e.getEntity();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("MancChallenge", null, "https://cdn.discordapp.com/avatars/919083946401222676/b4a215f4c968fa30dbd562f9fca21e6a.png?size=1024");
        embed.setTitle("Tótem de Resurrección por parte de `" + target.getName() + "`");
        embed.setColor(Color.ORANGE);
        if(getUseTotem(target) > 3){
            embed.setDescription("El jugador `" + target.getName() + "` ha usado más de 3 tótems de resurrección y ha sido eliminado del reto por un ***" + getCustomCause(Objects.requireNonNull(target.getLastDamageCause())) + "***.");
        } else {
            embed.setDescription("El jugador `" + target.getName() + "` ha usado un tótem de resurrección por un ***" + getCustomCause(Objects.requireNonNull(target.getLastDamageCause())) + "***, solo puede usar " + (3 - getUseTotem(target)) + " tótems.");
        }
        setInfo(embed, target);
        totemChannelNotify.sendMessage(new MessageBuilder().setEmbeds(embed.build()).build()).queue();
    }

    public static void sendDeathMessage(PlayerDeathEvent e){
        Player target = e.getEntity();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("MancChallenge", null, "https://cdn.discordapp.com/avatars/919083946401222676/b4a215f4c968fa30dbd562f9fca21e6a.png?size=1024");
        embed.setTitle("Muerte de `" + target.getName() + "`");
        embed.setDescription(TimeFormat.RELATIVE.now() + " ha perdido el reto en el día **" + getDay() + "** a causa de un ***" + getCustomCause(Objects.requireNonNull(e.getEntity().getLastDamageCause())) + "***.");
        embed.setColor(Color.RED);

        setInfo(embed, target);

        deathChannelNotify.sendMessage(new MessageBuilder().setEmbeds(embed.build()).build()).queue();
    }

    public static void sendCompleteReto(Player target){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("MancChallenge", null, "https://cdn.discordapp.com/avatars/919083946401222676/b4a215f4c968fa30dbd562f9fca21e6a.png?size=1024");
        embed.setTitle("El jugador `" + target.getName() + "` ha completado un reto del día **" + getDay() + "**.");
        embed.setDescription(TimeFormat.RELATIVE.now() + " completo el reto: " + Bossbar.getReto(getDay()) + "!");
        embed.setColor(Color.GREEN);

        setInfo(embed, target);

        completeRetosChannelNotify.sendMessage(new MessageBuilder().setEmbeds(embed.build()).build()).queue();
    }

    public static void sendNotCompleteReto(Player target){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("MancChallenge", null, "https://cdn.discordapp.com/avatars/919083946401222676/b4a215f4c968fa30dbd562f9fca21e6a.png?size=1024");
        embed.setTitle("El jugador `" + target.getName() + "` no completo el reto del día **" + getDaySurvived(target) + "**.");
        embed.setDescription(TimeFormat.RELATIVE.now() + " no completo el reto: " + Bossbar.getReto(getDaySurvived(target)) + "!");
        embed.setColor(Color.RED);

        setInfo(embed, target);

        completeRetosChannelNotify.sendMessage(new MessageBuilder().setEmbeds(embed.build()).build()).queue();
    }

}
