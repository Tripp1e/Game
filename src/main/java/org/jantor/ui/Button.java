package org.jantor.ui;

import greenfoot.Color;
import greenfoot.Greenfoot;
import org.jantor.utils.GreenfootImage;
import org.jantor.level.Level;
import org.jantor.screens.LevelSelection;
import org.jantor.screens.Options;

import static org.jantor.constants.PlayerInfo.buy;
import static org.jantor.constants.PlayerInfo.unlock;


public class Button extends Widget {
    Runnable action;
    boolean disabled = false;

    public enum ButtonType {
        SHOPCOIN,
        SHOPSTAR,
        LEVEL,
        OPTIONS,
        LEVELSELECTION,
        TEXT,
        GENERIC;

        public Runnable runnable(String str, Runnable run, int amount, int cost) {
            switch (this) {
                case SHOPCOIN:
                    return () -> buy("coin", str, amount, cost);
                case SHOPSTAR:
                    return () -> unlock("star", str, cost);
                case LEVEL:
                    return () -> Greenfoot.setWorld(new Level(str));
                case OPTIONS:
                    return () -> Greenfoot.setWorld(new Options());
                case LEVELSELECTION:
                    return () -> Greenfoot.setWorld(new LevelSelection());
                default:
                    return run;
            }
        }
    }

    public Button(String name, ButtonType type, String str, Runnable action, int amount, int cost) {
        super(name);
        this.action = type.runnable(str, action, amount, cost);

        image = getUpdatedImage();
        setImage();
    }
    public Button(String name, ButtonType type, String str) {
        this(name, type, str, () -> {}, 1, 1);
    }
    public Button(String name, ButtonType type, String str, int cost) {
        this(name, type, str, () -> {}, 1, cost);
    }
    public Button(String name, ButtonType type ) {
        this(name, type, null);
    }

    public void act() {
        if (disabled) return;

        if (Greenfoot.mouseClicked(this)) {
            action.run();
        }
        if (isHovered() && !isHovered) {
            isHovered = true;
            image = getHoveredImage();
            setImage();
        } else if (isHovered && !isHovered()) {
            isHovered = false;
            image = getUpdatedImage();
            setImage();
        }
    }

    private GreenfootImage getUpdatedImage() {
        GreenfootImage newImage = new org.jantor.utils.GreenfootImage("ui/button.png");
        newImage.setFont(font);
        newImage.addCenteredText(name, (int)(textMargin * 1.5) );
        return newImage;
    }
    private GreenfootImage getDisabledImage(String str) {
        GreenfootImage newImage = new org.jantor.utils.GreenfootImage("ui/button.png");
        newImage.setFont(font);
        newImage.setColor(new Color(255, 0, 0, 128));
        newImage.fill();
        newImage.addCenteredText(str, (int)(textMargin * 1.5) );
        return newImage;
    }
    private GreenfootImage getHoveredImage() {
        GreenfootImage newImage = new org.jantor.utils.GreenfootImage("ui/button.png");
        newImage.setFont(font);
        newImage.setColor(new Color(0, 0, 255, 60));
        newImage.fill();
        newImage.addCenteredText(name, (int)(textMargin * 1.5) );
        return newImage;
    }

    public void disable(String str) {
        if (disabled) return;
        disabled = true;
        image = getDisabledImage(str);
        setImage();
    }

    public void enable() {
        if (!disabled) return;
        disabled = false;
        image = getUpdatedImage();
        setImage();
    }
}