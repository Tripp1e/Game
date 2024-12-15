package org.jantor.shop;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.ui.Button;

import java.util.ArrayList;

public class Shop extends Actor {

    Button speedUp = new Button("Upgrade Speed", "speed");
    Button jumpUp = new Button("Upgrade Jump", "jumpStrength");

    ArrayList<Actor> buttons = new ArrayList<>();

    public Shop() {
        buttons.add(speedUp);
        buttons.add(jumpUp);
        buttons.add(this);

        Constants.world.addObject(speedUp, 200, 100);
        Constants.world.addObject(jumpUp, 200, 200);
    }

    public void act() {
        if (Greenfoot.isKeyDown("Escape")) getWorld().removeObjects(buttons);
    }

}
