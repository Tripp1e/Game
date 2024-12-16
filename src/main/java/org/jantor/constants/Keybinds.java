package org.jantor.constants;

import greenfoot.Greenfoot;

import java.util.HashMap;
import java.util.Objects;

public class Keybinds {

    public static HashMap<String, String> keybinds = new HashMap<String, String>() {{
        put("up", "up");
        put("down", "down");
        put("left", "left");
        put("right", "right");
        put("shop", "S");
        put("mainMenu", "M");
    }};

    public static String get(String key) {
        return keybinds.getOrDefault(key, "");
    }
    public static void set(String key, String value) { keybinds.put(key, value); }

    public static void setKeybind(String key) {
        String latestKey = Greenfoot.getKey();
        String newKeybind = "";
        while (Objects.equals(Greenfoot.getKey(), latestKey)) newKeybind = Greenfoot.getKey();
        set(key, newKeybind);
    }
}
