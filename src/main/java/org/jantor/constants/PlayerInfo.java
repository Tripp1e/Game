package org.jantor.constants;

import org.jantor.elements.Player;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;
import java.util.Map;

import static org.jantor.utils.JsonReader.getJsonObject;

public class PlayerInfo {

    public static HashMap<String, Object> data = new HashMap<>();

    public static Object get(String key) {
        return data.get(key);
    }
    public static Object get(String key, Object defaultValue) {
        Object value = get(key);
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

    public static Player getPlayer() {
        return Constants.renderer.player;
    }

    public static void save() {
        JSONObject jsonObject = new JSONObject(data);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("/saves/save.json"), StandardCharsets.UTF_8)) {
            writer.write(jsonObject.toString(4));
            System.out.println("save save save save save save");
        } catch (IOException ignored) { ignored.printStackTrace(); }
    }
    public static void load() {
        String content;
        InputStream inputStream = PlayerInfo.class.getResourceAsStream("/saves/save.json");
        if (inputStream == null) { System.err.println("Error: Could not find the save file"); return; }
        JSONObject saveJson = new JSONObject();
        try {
            saveJson = getJsonObject(inputStream);
        } catch (IOException ignored) {}
        HashMap<String, Object> map = new HashMap<>();

        JSONObject finalSaveJson = saveJson;
        saveJson.keySet().forEach(key -> map.put(key, finalSaveJson.get(key)));
        
        data = map;
    }
}
