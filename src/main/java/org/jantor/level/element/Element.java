package org.jantor.level.element;

import greenfoot.Actor;
import greenfoot.World;
import org.jantor.level.element.block.Stone;

public class Element extends Actor {
    public static int width = 50;
    public static int height = 50;

    public Element(String name) {
        setImage("resources/image/" + name);
    }

    @Override
    public void act() {

    }

    public void addTo(World world, int x, int y) {
        world.addObject(this, x * width, y * Stone.height);
    }



}
