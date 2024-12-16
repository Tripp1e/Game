package org.jantor.ui;

import greenfoot.Color;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

import java.awt.*;

public class Text extends Widget {
    int margin = 10;

    public Text(String name) {
        super(name);
        image = getTextImage();
        setImage();
    }
    public Text() { this(""); }

    public void setText(String text) {
        name = text;
        image = getTextImage();
        setImage();
    }
    private void setImage() { setImage(image); }

    private GreenfootImage getTextImage() {
        Vector2D size = getSize();

        GreenfootImage newImage = new GreenfootImage(size.x + margin, size.y + margin);
        newImage.setFont(font);
        newImage.setColor(Color.RED);

        newImage.drawString(name, 0, size.y);

        return newImage;
    }

    private Vector2D getSize() {
        Font awtFont = new Font(font.getName(), Font.BOLD, font.getSize());

        int w = 0;
        try {
            Graphics2D g2d = (Graphics2D) image.getAwtImage().getGraphics();
            g2d.setFont(awtFont);
            FontMetrics fm = g2d.getFontMetrics();
            w = fm.stringWidth(name);
        } catch (Exception e) {
            return new Vector2D(0, 0);
        }

        int h = font.getSize();
        return new Vector2D(w, h);
    }
}