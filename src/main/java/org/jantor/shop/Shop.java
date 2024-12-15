package org.jantor.shop;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.constants.PlayerInfo;
import org.jantor.ui.Button;
import org.jantor.ui.Button.ButtonType;
import org.jantor.utils.GreenfootImage;

import java.util.ArrayList;

public class Shop extends Actor {

    Button speedUp =    new Button("Upgrade Speed",     ButtonType.SHOPCOIN, "speed"                );
    Button jumpUp =     new Button("Upgrade Jump",      ButtonType.SHOPCOIN, "jumpStrength"         );

    Button doubleJump = new Button("Unlock DoubleJump", ButtonType.SHOPSTAR, "doubleJump", 3    );

    ArrayList<Actor> buttons = new ArrayList<>();

    public Shop() {

        Constants.world.addObject(speedUp, 250, 150);
        Constants.world.addObject(jumpUp, 250, 250);
        Constants.world.addObject(doubleJump, 950, 150);

        setImage(new GreenfootImage("ui/shopBackground.png"));

        buttons.add(speedUp);
        buttons.add(jumpUp);
        buttons.add(doubleJump);
        buttons.add(this);
    }

    public void act() {
        if (Greenfoot.isKeyDown("Escape")) getWorld().removeObjects(buttons);
        if ((Boolean) PlayerInfo.get("doubleJump", false)) {
            doubleJump.disable("2x Jump unlocked");
        }
    }

}
