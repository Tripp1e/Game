package org.jantor.ui;

import greenfoot.Font;
import org.jantor.utils.GreenfootImage;

public class Counter extends Widget {
    int counter;

    public Counter(String name, int counter) {
        super(name);
        this.counter = counter;

        image = new GreenfootImage("ui/button.png");
        Font font = new Font("Comic Sans MS", true, false, 25);

        image.setFont(font);
        image.drawString(name + ": " + counter, 15, 35);

        setImage(image);
    }

    public void increment() {
        counter++;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void act() {
        image = new GreenfootImage("ui/button.png");
        Font font = new Font("Comic Sans MS", true, false, 25);

        image.setFont(font);
        image.drawString(name + ": " + counter, 15, 35);

        setImage(image);
    }
}
