package org.jantor.ui;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;
import org.jantor.utils.GreenfootImage;

import java.util.Objects;

public class Counter extends Widget {
    int counter;
    Actor iconActor;

    public Counter(String name, int counter) {
        super(name);
        this.counter = counter;

        clearImage();
        addIcon();
    }
    public Counter(String name) {
        this(name, 0);
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void act() {
        clearImage();
        setImage(image);
    }

    private void clearImage() {
        image = new GreenfootImage(100, 50);
        Font font = new Font("Comic Sans MS", true, false, 25);

        image.setColor(Color.BLACK);
        image.setFont(font);
        image.drawString(Objects.toString(this.counter), 45, 35);

        if (!(getWorld() != null && iconActor.getWorld() == null)) return;
        getWorld().addObject(iconActor, getX() - 50, getY());
        System.out.println("Icon placed!");
    }

    private void addIcon() {
        GreenfootImage icon = new GreenfootImage("images/collectables/" + name.toLowerCase() + ".png");
        iconActor = new Actor(){};
        iconActor.setImage(icon);
    }
}
