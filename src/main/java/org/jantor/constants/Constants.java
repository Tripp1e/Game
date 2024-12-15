package org.jantor.constants;

import greenfoot.World;
import org.jantor.elements.Player;
import org.jantor.ui.Button;
import org.jantor.ui.Counter;
import org.jantor.utils.Renderer;
import org.jantor.utils.Vector2D;

import java.util.HashMap;


public class Constants {

    public static final Vector2D elementSize = new Vector2D(50, 50);

    public static final Vector2D elementOffset = new Vector2D(25, 25);

    public static final Vector2D screenSize = new Vector2D(1200, 800);

    public static final Vector2D originOffset = new Vector2D(0, Constants.screenSize.y);

    public static final Vector2D zeroVector = new Vector2D(0, 0);

    public static Renderer renderer;

    public static World world;

    public static int coinAmount = 0;

    public static Counter coinCounter = new Counter("Coins", 0);

}
