package org.jantor.ui;

import greenfoot.Actor;

public abstract class Widget extends Actor {
    public static int width = 150;
    public static int height = 70;
    public String name;

    public Widget(String name) {
        this.name = name;
    }
    
}
