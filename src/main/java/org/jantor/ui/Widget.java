package org.jantor.ui;

import greenfoot.Actor;
import org.jantor.utils.GreenfootImage;

public abstract class Widget extends Actor {
    public String name;
    GreenfootImage image;

    public Widget(String name) {
        this.name = name;
    }

}
