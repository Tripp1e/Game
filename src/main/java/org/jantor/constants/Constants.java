package org.jantor.constants;

import greenfoot.World;
import org.jantor.ui.Counter;
import org.jantor.ui.Text;
import org.jantor.utils.Renderer;
import org.jantor.utils.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Constants {

    public static final Vector2D elementSize = new Vector2D(50, 50);
    public static final Vector2D elementOffset = new Vector2D(25, 25);
    public static final Vector2D screenSize = new Vector2D(1200, 800);
    public static final Vector2D originOffset = new Vector2D(0, Constants.screenSize.y);

    public static final Vector2D zeroVector = new Vector2D(0, 0);
    public static Renderer renderer;
    public static World world;

    public static Text help = new Text("Help");

    public static Counter coinCounter = new Counter("Coin");
    public static Counter starCounter = new Counter("Star");
    public static void updateCounters() {
        coinCounter.setCounter((int) PlayerInfo.get("coin", 0));
        starCounter.setCounter((int) PlayerInfo.get("star", 0));
    }
    public static void resetCounters() {
        PlayerInfo.resetToOld();
        updateCounters();
    }

    public static int currentLevel = 0;
    public static HashMap<String, Integer> levelNames = new HashMap<String, Integer>() {{
        put("one", 1);
        put("two", 2);
        put("three", 3);
    }};
    public static HashMap<Integer, String> levelNamesReversed = new HashMap<Integer, String>() {{
        put(1, "one");
        put(2, "two");
        put(3, "three");
    }};

    public static String getCurrentLevel() {
        return levelNamesReversed.getOrDefault(currentLevel, "one");
    }




}
