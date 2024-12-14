package org.jantor.ui;

import greenfoot.Color;
import greenfoot.Font;
import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.utils.GreenfootImage;
import org.jantor.screens.Screen;
import org.jantor.utils.Vector2D;
import org.reactfx.util.LL;


public class Button extends Widget {
    Runnable link;
    Color color;
    Color fontColor;

    public Button(String name, Runnable link, Color color, Color fontColor) {
        super(name);
        image = new GreenfootImage("ui/button.png");
        this.link = link;

        this.color = color;
        this.fontColor = fontColor;

        Font font = new Font("Comic Sans MS", true, false, 25);

        image.setFont(font);
        image.drawString(name, 15, 35);

        setImage(image);

    }

    public Button(String name, Runnable link) {
        this(name, link, Color.BLACK, Color.WHITE);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            link.run();
        }
    }
}