package org.jantor.ui;

import greenfoot.Actor;
import greenfoot.Color;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

public class Counter extends Widget {
    int counter;
    Actor iconActor;

    public Counter(String name, int counter) {
        super(name);
        this.counter = counter;
        image = getUpdatedImage();
        setIconActor();
        setImage();
    }
    public Counter(String name) {
        this(name, 0);
    }

    private GreenfootImage getUpdatedImage() {
        GreenfootImage newImage = new org.jantor.utils.GreenfootImage(100, 50);
        newImage.setColor(Color.BLACK);
        newImage.setFont(font);
        newImage.addText(String.valueOf(counter), new Vector2D(45, 35), 0);
        return newImage;
    }

    private void setIconActor() {
        if (iconActor != null) return;

        GreenfootImage icon = new GreenfootImage("images/collectables/" + name.toLowerCase() + ".png");
        Actor iconActor = new Actor(){};
        iconActor.setImage(icon);
        this.iconActor = iconActor;
    }

    public void setIcon() {
        if (getWorld() == null) return;
        if (getWorld().getObjects(Actor.class).contains(iconActor)) return;
        getWorld().removeObject(iconActor);
        getWorld().addObject(iconActor, getX() - 50, getY());
    }

    public void setCounter(int counter) {
        this.counter = counter;
        image = getUpdatedImage();
        setImage();
        setIcon();
    }

}
