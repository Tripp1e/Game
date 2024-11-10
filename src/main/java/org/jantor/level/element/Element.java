package org.jantor.level.element;

import greenfoot.Actor;
import greenfoot.World;
import org.jantor.image.ElementImage;
import org.jantor.level.element.block.Stone;

public class Element extends Actor {
    public static int width = 50;
    public static int height = 50;

    public Element(ElementImage image) {
        setImage(image);
    }

    public void addTo(World world, int x, int y) {
        world.addObject(this, x * width, y * Stone.height);
    }

}
