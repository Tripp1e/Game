package org.jantor.utils;

import greenfoot.Color;
import greenfoot.Font;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GreenfootImage extends greenfoot.GreenfootImage {

    public GreenfootImage(String filename) {
        super(filename);
    }

    public GreenfootImage(int width, int height) {
        super(width, height);
    }
    public GreenfootImage(Vector2D vec) {
        this(vec.x, vec.y);
    }

    public void addBackground(Color color) {
        setColor(color);
        fill();
    }

    public void addText(String text, Vector2D textPos, int margin, Color color) {
        setFont(getFont());
        setColor(color);

        drawString(text, textPos.x + margin, textPos.y - margin);
    }
    public void addText(String text, Vector2D textPos, int margin) {
        addText(text, textPos, margin, Color.WHITE);
    }
    public void addCenteredText(String text, int margin, Color color) {
        addText(text, new Vector2D(0, getHeight()), margin, color);
    }
    public void addCenteredText(String text, int margin) {
        addCenteredText(text, margin, Color.WHITE);
    }

    public static Vector2D getStingSize(String text, Font font, GreenfootImage image) {
        java.awt.Font awtFont = new java.awt.Font(font.getName(), java.awt.Font.BOLD, font.getSize());
        int w;
        try {
            Graphics2D g2d = (Graphics2D) image.getAwtImage().getGraphics();
            g2d.setFont(awtFont);
            FontMetrics fm = g2d.getFontMetrics();
            w = fm.stringWidth(text);
        } catch (Exception e) {
            return new Vector2D(0, 0);
        }

        int h = font.getSize();
        return new Vector2D(w, h);
    }

    public GreenfootImage mirror() {
        BufferedImage bufferedImage = this.getAwtImage();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bufferedImage.getWidth(), 0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage flippedImage = op.filter(bufferedImage, null);

        GreenfootImage flippedGreenfootImage = new GreenfootImage(flippedImage.getWidth(), flippedImage.getHeight());
        Graphics2D g = flippedGreenfootImage.getAwtImage().createGraphics();
        g.drawImage(flippedImage, 0, 0, null);
        g.dispose();

        return flippedGreenfootImage;
    }

}
