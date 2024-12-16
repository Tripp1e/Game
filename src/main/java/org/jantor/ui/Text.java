package org.jantor.ui;

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
        image.addText(name, textMargin);
        setImage();
    }
    private void setImage() { setImage(image); }

    private GreenfootImage getTextBackground() {
        Vector2D size = getNameSize();
        GreenfootImage image = new GreenfootImage(size.x + 2 * textMargin, size.y + 2 * textMargin);
        image.setFont(font);
        System.out.println(image.getWidth());
        return image;
    }
    private Vector2D getNameSize() {
        return GreenfootImage.getStingSize(name, font, image);
    }
}