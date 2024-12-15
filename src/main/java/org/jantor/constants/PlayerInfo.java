package org.jantor.constants;

import org.jantor.elements.Player;

import java.util.HashMap;
import java.util.Objects;

public class PlayerInfo {

    public static HashMap<String, Object> data = new HashMap<>();
    public static Object get(String key) {
        return data.get(key);
    }
    public static void set(String key, Object value) {
        data.put(key, value);
    }
    public static void add(String key, int value) {
        Object current = data.get(key);
        if (!(current instanceof Integer)) return;
        set(key, ((int) current) + value);
        if (Objects.equals(key, "coins")) Constants.coinCounter.setCounter((int) PlayerInfo.data.get("coins"));
    }
    public static void setInitial(String key, Object value) {
        if (get(key) != null) return;
        set(key, value);
    }

    public static boolean buy(String attribute, int amount, int cost) {
        int coins = (int) get("coins");
        if ((coins < cost )) return false;
        add("coins", -1);
        add(attribute, amount);
        return true;
    }
    public static boolean buy(String attribute, int amount) {
        return buy(attribute, amount, 1);
    }
    public static Player getPlayer() {
        return Constants.renderer.player;
    }
}
