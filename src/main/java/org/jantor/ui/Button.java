package org.jantor.ui;

import greenfoot.Color;
import greenfoot.Greenfoot;
import org.jantor.level.Level;
import org.jantor.screens.LevelSelection;
import org.jantor.screens.Options;
import org.jantor.utils.GreenfootImage;

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

        image = new GreenfootImage("ui/button.png");
        image.setFont(font);
        image.drawString(name, 15, 35);

        setImage(image);
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
            disable(name);
            action.run();
        }
    }

    public void disable(String str) {
        if (disabled) return;
        disabled = true;
        image.setColor(new Color(255, 0, 0, 128));
        image.drawRect(0,0, image.getWidth(), image.getHeight());
        image.fill();
        image.setColor(Color.WHITE);
        image.drawString(str, 15, 35);
    }
}