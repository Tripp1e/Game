package org.jantor.screens;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.jantor.constants.Constants;
import org.reactfx.util.LL;

public class Screen extends World {
    public static final Color backgroundColor = Color.WHITE;

    public Screen() {
        super(Constants.screenSize.x, Constants.screenSize.y, 1);
    }

    void setBackground() {
        GreenfootImage background = getBackground();

        background.setColor(backgroundColor);
        background.fill();
    }

    void addImage(GreenfootImage image, int x, int y) {
        Actor imageActor = new Actor() {
            @Override
            public void act() {
                setImage(image);
            }
        };
        addObject(imageActor, x, y);
    }

}
