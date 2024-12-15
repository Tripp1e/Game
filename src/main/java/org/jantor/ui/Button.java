package org.jantor.ui;

import greenfoot.Color;
import greenfoot.Font;
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
        TEXT;

        public Runnable runnable(String str, Runnable run, int amount, int cost) {
            switch (this) {
                case SHOPCOIN:
                    return () -> {
                        boolean bought = buy("coin", str, amount, cost);
                    };
                case SHOPSTAR:
                    return () -> {
                        boolean unlocked = unlock("star", str, cost);
                    };
                case LEVEL:
                    return () -> {
                        Level level = new Level(str);
                        Greenfoot.setWorld(level);
                    };
                case OPTIONS:
                    return () -> {
                        Options optionScreen = new Options();
                        Greenfoot.setWorld(optionScreen);
                    };
                case LEVELSELECTION:
                    return () -> {
                        LevelSelection levelSelection = new LevelSelection();
                        Greenfoot.setWorld(levelSelection);
                    };
                case TEXT:
                    return () -> {};
                default:
                    return run;
            }
        }
    }

    public Button(String name, ButtonType type, String str, Runnable action, int amount, int cost) {
        super(name);
        image = new GreenfootImage("ui/button.png");
        this.action = type.runnable(str, action, amount, cost);

        Font font = new Font("Comic Sans MS", true, false, 25);

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