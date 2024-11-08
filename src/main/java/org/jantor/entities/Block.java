package org.jantor.entities;

import greenfoot.Actor;
import greenfoot.World;

public class Block extends Actor {
    public static int width = 50;
    public static int height = 50;

    public Block(String name) {
        setImage("resources/image/" + name + ".png");
    }
    public void addTo(World world, int x, int y) {
        world.addObject(this, x * width, y * Stone.height);
    }
}
