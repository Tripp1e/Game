package org.jantor.entities;
import greenfoot.Greenfoot;

public class Player extends Block {

    public Player() {
        super("player");
    }

    @Override
    public void act() {
        movePlayer();
    }

    private void movePlayer() {
        int speed = 5;
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX() , getY() - speed);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX() , getY() + speed);
        }
    }
}
