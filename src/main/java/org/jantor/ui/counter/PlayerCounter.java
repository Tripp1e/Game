package org.jantor.ui.counter;

import greenfoot.Color;
import org.jantor.constants.Constants;
import org.jantor.ui.Widget;
import org.jantor.utils.GreenfootImage;

public class PlayerCounter extends Widget {
    public PlayerCounter(String name) {
        super(name);
        setHitbox();
    }

    public void setHitbox() {
        GreenfootImage button = new GreenfootImage(Constants.elementWidth, Constants.elementHeight);
        button.drawRoundRect(15, Constants.elementWidth, Constants.elementHeight, Color.BLACK);

        button.setColor(Color.WHITE);
        button.drawString(name, Constants.elementWidth / 2, Constants.elementHeight / 2);

        setImage(button);
    }

    public void setName(String name) {
        this.name = name;
    };
}
