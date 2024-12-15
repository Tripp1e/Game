package org.jantor.constants;

import greenfoot.World;
import org.jantor.ui.Counter;
import org.jantor.utils.Renderer;
import org.jantor.utils.Vector2D;


public class Constants {

    public static final Vector2D elementSize = new Vector2D(50, 50);
    public static final Vector2D elementOffset = new Vector2D(25, 25);
    public static final Vector2D screenSize = new Vector2D(1200, 800);
    public static final Vector2D originOffset = new Vector2D(0, Constants.screenSize.y);

    public static final Vector2D zeroVector = new Vector2D(0, 0);
    public static Renderer renderer;
    public static World world;

    public static Counter coinCounter = new Counter("Coin");
    public static Counter starCounter = new Counter("Star");
    public static void updateCounters() {
        coinCounter.setCounter((int) PlayerInfo.get("coin"));
        starCounter.setCounter((int) PlayerInfo.get("star"));
    }

}
