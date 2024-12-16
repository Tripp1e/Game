package org.jantor.ui;

import greenfoot.*;
import org.jantor.constants.Constants;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

public abstract class Widget extends Actor {
    public String name;
    GreenfootImage image;
    Font font = new Font("Comic Sans MS", true, false, 25);
    int textMargin = 10;

    boolean isHovered = false;

    public Widget(String name) {
        this.name = name;
    }

    public void addToMiddle(World world) {
        world.addObject(this, Constants.screenSize.x / 2, Constants.screenSize.y / 2);
    }
    protected Vector2D getNameSize() {
        return GreenfootImage.getStingSize(name, font, image);
    }
    protected void setImage() { setImage(image); }

    protected boolean isHovered() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) {
            return false;
        }
        int mouseX = mouse.getX();
        int mouseY = mouse.getY();

        int halfWidth = image.getWidth() / 2;
        int halfHeight = image.getHeight() / 2;

        int minX = getX() - halfWidth;
        int maxX = getX() + halfWidth;
        int minY = getY() - halfHeight;
        int maxY = getY() + halfHeight;

        return minX <= mouseX && mouseX <= maxX && minY <= mouseY && mouseY <= maxY;
    }

}