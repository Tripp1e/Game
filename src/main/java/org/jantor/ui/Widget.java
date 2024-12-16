package org.jantor.ui;

import greenfoot.Actor;
import greenfoot.Font;
import greenfoot.World;
import org.jantor.constants.Constants;
import org.jantor.utils.GreenfootImage;

public abstract class Widget extends Actor {
    public String name;
    GreenfootImage image;
    Font font = new Font("Comic Sans MS", true, false, 25);

    int textMargin = 10;

    public Widget(String name) {
        this.name = name;
    }

    public void addToMiddle(World world) {
        world.addObject(this, Constants.screenSize.x / 2, Constants.screenSize.y / 2);
    }

}