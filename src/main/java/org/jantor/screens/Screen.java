package org.jantor.screens;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.jantor.constants.Constants;
import org.reactfx.util.LL;

public class Screen extends World {
    public static final Color backgroundColor = Color.WHITE;

    public Screen() {
        super(Constants.screenWidth, Constants.screenHeight, 1);
    }

    void setBackground() {
        GreenfootImage background = getBackground();

        background.setColor(backgroundColor);
        background.fill();
    }

}
