package org.jantor.ui;

import greenfoot.Color;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

public class Text extends Widget {

    public Text(String name) {
        super(name);
        setText(name);
    }
    public Text() { this(""); }

    public void setText(String text) {
        name = text;
        image = getTextBackground();
        image.addBackground(new Color(128, 128, 128, 200));
        image.addCenteredText(name, textMargin, Color.RED);
        setImage();
    }

    private GreenfootImage getTextBackground() {
        Vector2D size = getNameSize();
        GreenfootImage image = new GreenfootImage(size.x + 2 * textMargin, size.y + 2 * textMargin);
        image.setFont(font);
        return image;
    }
}