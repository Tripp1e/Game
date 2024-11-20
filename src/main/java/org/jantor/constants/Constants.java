package org.jantor.constants;

import org.jantor.elements.Block;
import org.jantor.elements.Player;
import org.jantor.elements.movement.BlockMovement;
import org.jantor.utils.Vector2D;

import java.util.ArrayList;

public class Constants {

    public static int elementWidth = 50;
    public static int elementHeight = 50;
    public static int elementXOffset = 10;
    public static int elementYOffset = 10;

    public static final int screenWidth = 1200;
    public static final int screenHeight = 800;

    public static final Vector2D zeroVector = new Vector2D(0, 0);

    public static Player player;
    public static ArrayList<Block> blocks;

}
