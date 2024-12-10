package org.jantor.ui;

import greenfoot.Color;
import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.utils.GreenfootImage;
import org.jantor.screens.Screen;
import org.jantor.utils.Vector2D;
import org.reactfx.util.LL;


public class Button extends Widget {
    Screen link;
    Color color;
    Color fontColor;

    public Button(String name, Screen link, Color color, Color fontColor) {
        super(name);
        this.link = link;

        this.color = color;
        this.fontColor = fontColor;

        setHitbox();
    }

    public Button(String name, Screen link) {
        this(name, link, Color.BLACK, Color.WHITE);
    }

    public void setHitbox() {
        GreenfootImage button = new GreenfootImage(Constants.elementSize);
        button.drawRoundRect(15, Constants.elementSize, color);

        button.setColor(fontColor);
        button.drawString(name, Vector2D.copyFrom(Constants.elementSize).multiply(0.5));

        setImage(button);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(link);
        }
    }
}