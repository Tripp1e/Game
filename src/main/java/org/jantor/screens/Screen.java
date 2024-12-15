package org.jantor.screens;

import greenfoot.Color;
import greenfoot.World;
import org.jantor.constants.Constants;

public class Screen extends World {
    public static final Color backgroundColor = Color.WHITE;

    public Screen() {
        super(Constants.screenSize.x, Constants.screenSize.y, 1);
    }

}
