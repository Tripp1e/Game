package org.jantor.ui;

import greenfoot.Greenfoot;
import org.jantor.screens.Screen;

public class Clickable extends Button {
    Runnable runnable;

    public Clickable(String name, Runnable runnable) {
        super(name, new Screen());
        this.runnable = runnable;
    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            runnable.run();
        }
    }
}
