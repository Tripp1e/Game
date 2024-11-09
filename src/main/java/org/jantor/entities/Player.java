package org.jantor.entities;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import org.jantor.mixin.PlayerImage;

public class Player extends Block {

    static PlayerImage walking = new PlayerImage("walking.gif");
    static PlayerImage standing = new PlayerImage("standing.png");
    static PlayerImage jumping = new PlayerImage("jumping.png");
    //static PlayerImage crouching = new PlayerImage("crouching.png");

    static int left = 180;
    static int right = 0;
    static int up = 270;
    static int down = 90;

    public Player() {
        super("player/standing");
    }

    @Override
    public void act() {
        movePlayer();
    }

    private void movePlayer() {
        int speed = 5;
        if (Greenfoot.isKeyDown("left")) {
            setImage(walking);
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setImage(walking.getMirroredHorizontally());
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
            setImage(jumping);
            setLocation(getX() , getY() - speed);
        }
        if (Greenfoot.isKeyDown("down")) {
            setImage(walking);
            setLocation(getX() , getY() + speed);
        }

        //if (getRotation() == left) setImage(walking);
        //else if (getRotation() == right) setImage(walking.getMirroredHorizontally());

    }
}
