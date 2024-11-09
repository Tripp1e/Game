package org.jantor.widgets;

import greenfoot.*;
import greenfoot.Color;
import org.jantor.screens.Screen;
import static org.jantor.mixin.GreenfootImage.drawRoundRect;


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
        GreenfootImage button = new GreenfootImage(width, height);
        drawRoundRect(button, 15, width, height, color);

        button.setColor(fontColor);
        button.drawString(name, width / 2, height / 2);

        setImage(button);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(link);
        }
    }






}