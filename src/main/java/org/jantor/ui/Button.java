package org.jantor.ui;

import greenfoot.Font;
import greenfoot.Greenfoot;
import org.jantor.utils.GreenfootImage;

import static org.jantor.constants.PlayerInfo.buy;


public class Button extends Widget {
    Runnable link;

    public Button(String name, Runnable link) {
        super(name);
        image = new GreenfootImage("ui/button.png");
        this.link = link;

        Font font = new Font("Comic Sans MS", true, false, 25);

        image.setFont(font);
        image.drawString(name, 15, 35);

        setImage(image);

    }
    public Button(String name, String attribute, int cost, int amount) {
        this(name, () -> {
            boolean bought = buy(attribute, cost, amount);
            if (!bought) System.out.println("Not enough coins to buy");
        });
    }
    public Button(String name, String attribute) {
        this(name, attribute, 1, 1);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            link.run();
        }
    }
}