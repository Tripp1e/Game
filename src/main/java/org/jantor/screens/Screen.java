package org.jantor.screens;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Screen extends World {
    public static final int width = 1200;
    public static final int height = 800;
    public static final Color backgroundColor = Color.WHITE;

    public Screen() {
        super(width,height, 1);
    }

    void setBackground() {
        GreenfootImage background = getBackground();

        background.setColor(backgroundColor);
        background.fill();
    }

}
