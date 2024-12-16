package org.jantor.screens;

import greenfoot.Greenfoot;
import greenfoot.World;
import org.jantor.constants.Constants;
import org.jantor.constants.Keybinds;
import org.jantor.shop.Shop;
import org.jantor.utils.GreenfootImage;

public class Screen extends World {
    GreenfootImage background = new GreenfootImage("ui/mainBackground.png").mirror();

    public Screen() {
        super(Constants.screenSize.x, Constants.screenSize.y, 1);
        setBackground(background);
    }

    public void act() {
        if ( Greenfoot.isKeyDown(Keybinds.get("mainMenu")) && getObjects(Shop.class).isEmpty()) {
            Constants.resetCounters();
            Greenfoot.setWorld(new Main());
        }
    }
}
