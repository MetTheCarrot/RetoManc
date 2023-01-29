package carrot.mc.mancchallenge.Discord;

import carrot.mc.mancchallenge.Data.CreateFile;
import carrot.mc.mancchallenge.Main;

public class DiscordData {

    private static final boolean debug = false;
    private static final CreateFile data = new CreateFile(Main.getPlugin(), "discord.yml");

    public static void setData(String id, String value){
        data.setConfig(id, value);
    }

    public static String getData(String id, String defaultValue){
        if(data.getConfig().getString(id) == null){
            setData(id, defaultValue);
        }
        return data.getConfig().getString(id);
    }

    public static String getToken(){
        if(debug) return "que buscas crack?";
        else return getData("token", "");
    }

    public static String getDeathChannel(){
        if(debug) return "1066030685309128714";
        else return getData("canales.muerte", "");
    }

    public static String getRetosChatChannel(){
        if(debug) return "1066402344620806256";
        else return getData("canales.reto", "");
    }

    public static String getTotemChatChannel(){
        if(debug) return "1066402371661467728";
        else return getData("canales.totem", "");
    }

}
