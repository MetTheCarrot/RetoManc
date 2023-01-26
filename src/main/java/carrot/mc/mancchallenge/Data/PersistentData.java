package carrot.mc.mancchallenge.Data;

import carrot.mc.mancchallenge.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public class PersistentData {

    public static <T, Z> void set(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type, Z value) {
        PersistentData.getDataContainer(holder).set(PersistentData.Key(key), type, value);
    }

    private static PersistentDataContainer getDataContainer(PersistentDataHolder holder){
        return holder.getPersistentDataContainer();
    }

    public static <T, Z> Z get(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type) {
        if(!PersistentData.has(holder, key, type)){
            return null;
        }
        return PersistentData.getDataContainer(holder).get(PersistentData.Key(key), type);
    }

    public static <T, Z> boolean has(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type) {
        if(holder == null || holder.getPersistentDataContainer() == null || holder.getPersistentDataContainer().get(PersistentData.Key(key), type) == null)return false;
        return PersistentData.getDataContainer(holder).has(PersistentData.Key(key), type);
    }

    public static PersistentDataContainer newDataContainer(PersistentDataHolder holder){
        return holder.getPersistentDataContainer().getAdapterContext().newPersistentDataContainer();
    }


    public static <T, Z> boolean equals(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type, Z value) {
        if(!PersistentData.has(holder, key, type)) return false;
        return PersistentData.get(holder, key, type).equals(value) || value.equals(PersistentData.get(holder, key, type));
    }

    public static NamespacedKey Key(String string){
        return new NamespacedKey(Main.getPlugin(), string);
    }

    public static PersistentDataType<Integer, Integer> INT = PersistentDataType.INTEGER;
    public static PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    public static PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    public static PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;

}
