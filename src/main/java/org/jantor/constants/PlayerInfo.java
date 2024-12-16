package org.jantor.constants;

import org.jantor.elements.Player;
import java.util.HashMap;

public class PlayerInfo {

    public static HashMap<String, Object> data = new HashMap<>();
    public static HashMap<String, Object> oldData = new HashMap<>();

    public static void syncToOld() {
        oldData.clear();
        oldData.putAll(data);
    }
    public static void resetToOld() {
        data.clear();
        data.putAll(oldData);
    }

    public static Object get(String key) {
        return data.get(key);
    }
    public static Object get(String key, Object defaultValue) {
        Object value = get(key);
        if (value == null) return defaultValue;
        return value;
    }
    public static Object getOld(String key) {
        return oldData.get(key);
    }
    public static Object getOld(String key, Object defaultValue) {
        Object value = getOld(key);
        if (value == null) return defaultValue;
        return value;
    }

    public static void   set(String key, Object value) {
        data.put(key, value);
    }
    public static void   setInitial(String key, Object value) {
        if (get(key) != null) return;
        set(key, value);
    }

    public static void   add(String key, int value) {
        Object current = data.get(key);
        if (!(current instanceof Integer)) return;
        set(key, ((int) current) + value);
        Constants.updateCounters();
    }

    public static boolean buy(String currency, String attribute, int amount, int cost) {
        int curr = (int) get(currency);
        if ((curr < cost )) return false;
        add(currency, -cost);
        add(attribute, amount);
        Constants.updateCounters();
        return true;
    }
    public static boolean unlock(String currency, String attribute, int cost) {
        int curr = (int) get(currency);
        if ((curr < cost )) return false;
        add(currency, -cost);
        set(attribute, true);
        return true;
    }

}
