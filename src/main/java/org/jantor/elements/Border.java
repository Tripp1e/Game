package org.jantor.elements;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import org.jantor.constants.Constants;

public class Border extends Element {
    public BorderDirection direction;

    public enum BorderDirection {
        LEFT,
        RIGHT;

        BorderDirection() {}
    }

    public Border(BorderDirection direction) {
        super(new GreenfootImage(Constants.screenWidth / 4, Constants.screenHeight));
        this.direction = direction;
    }

}
