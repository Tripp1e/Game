package org.jantor.elements;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.jantor.constants.Constants;

public class Element extends Actor {

    public Element(GreenfootImage image) {
        setImage(image);
    }

    public void addTo(World world, int x, int y) {
        world.addObject(this, x * Constants.elementWidth + Constants.elementXOffset, y * Constants.elementHeight - Constants.elementYOffset);
    }

}
