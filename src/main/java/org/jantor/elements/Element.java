package org.jantor.elements;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Element extends Actor {
    public static int width = 50;
    public static int height = 50;

    public Element(GreenfootImage image) {
        setImage(image);
    }

    public void addTo(World world, int x, int y) {
        world.addObject(this, x * width, y * height);
    }

}
