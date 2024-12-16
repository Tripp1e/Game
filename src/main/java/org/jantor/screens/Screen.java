package org.jantor.screens;

import greenfoot.World;
import org.jantor.constants.Constants;
import org.jantor.utils.GreenfootImage;

public class Screen extends World {
    GreenfootImage background = new GreenfootImage("ui/mainBackground.png").mirror();

    public Screen() {
        super(Constants.screenSize.x, Constants.screenSize.y, 1);
        setBackground(background);
    }
}
