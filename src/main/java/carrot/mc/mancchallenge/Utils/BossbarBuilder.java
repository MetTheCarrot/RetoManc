package carrot.mc.mancchallenge.Utils;

import carrot.mc.mancchallenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import static carrot.mc.mancchallenge.Utils.Chat.color;

public class BossbarBuilder {

    protected BossBar bossbar;
    private String title;
    private BarColor BarColor;
    private final NamespacedKey key;
    private BarStyle style;

    public BossbarBuilder(NamespacedKey key){
        this.key = key;
    }

    public BossbarBuilder setTitle(String title){
        this.title = color(title);
        return this;
    }

    public BossbarBuilder setColor(BarColor color){
        this.BarColor = color;
        return this;
    }

    public BossbarBuilder setStyle(BarStyle style){
        this.style = style;
        return this;
    }

    public BossBar build(){
        bossbar = Bukkit.createBossBar(key, title, BarColor, style);
        bossbar.setVisible(true);
        return bossbar;
    }

    public static NamespacedKey setKey(String key){
        return new NamespacedKey(Main.getPlugin(), key);
    }

}
