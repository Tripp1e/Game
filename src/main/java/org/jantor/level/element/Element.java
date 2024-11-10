package org.jantor.level.element;

import greenfoot.Actor;
import greenfoot.World;
import org.jantor.image.ElementImage;
import org.jantor.level.element.block.Stone;

public abstract class Element extends Actor {
    public static int width = 50;
    public static int height = 50;

    public Element(ElementImage img) {
        setImage(img);
    }

    public void addTo(World world, int x, int y) {
        world.addObject(this, x * width, y * Stone.height);
    }
}
