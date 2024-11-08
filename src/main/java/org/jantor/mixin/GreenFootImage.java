package org.jantor.mixin;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import org.jantor.screens.Screen;

public class GreenFootImage extends GreenfootImage {
    public GreenFootImage(String filename) throws IllegalArgumentException {
        super(filename);
    }

    public static void drawRoundRect(GreenfootImage image, int borderRadius, int width, int height, Color color) {
        image.setColor(Screen.backgroundColor);
        image.fill();

        image.setColor(color);

        image.fillOval(0, 0, borderRadius * 2, borderRadius * 2);
        image.fillOval(width - borderRadius * 2, 0, borderRadius * 2, borderRadius * 2);
        image.fillOval(0, height - borderRadius * 2, borderRadius * 2, borderRadius * 2);
        image.fillOval(width - borderRadius * 2, height - borderRadius * 2, borderRadius * 2, borderRadius * 2);

        image.fillRect(borderRadius, 0, width - 2 * borderRadius, height);
        image.fillRect(0, borderRadius, width, height - 2 * borderRadius);
    }


}
