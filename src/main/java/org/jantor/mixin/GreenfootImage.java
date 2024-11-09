package org.jantor.mixin;

import greenfoot.Color;
import org.jantor.screens.Screen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GreenfootImage extends greenfoot.GreenfootImage {
    public GreenfootImage(String filename) throws IllegalArgumentException {
        super(filename);
    }

    public static void drawRoundRect(greenfoot.GreenfootImage image, int borderRadius, int width, int height, Color color) {
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

    public greenfoot.GreenfootImage getMirroredHorizontally() {
        BufferedImage bufferedImage = this.getAwtImage();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bufferedImage.getWidth(), 0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage flippedImage = op.filter(bufferedImage, null);

        greenfoot.GreenfootImage flippedGreenfootImage = new greenfoot.GreenfootImage(flippedImage.getWidth(), flippedImage.getHeight());
        Graphics2D g = flippedGreenfootImage.getAwtImage().createGraphics();
        g.drawImage(flippedImage, 0, 0, null);
        g.dispose();

        return flippedGreenfootImage;
    }

}
