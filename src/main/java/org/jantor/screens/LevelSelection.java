package org.jantor.screens;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.level.Level;
import org.jantor.ui.Button;

import java.util.ArrayList;

public class LevelSelection extends Screen {
    private final ArrayList<Button> levels = new ArrayList<>();

    public LevelSelection() {
        addLevels();

        int y = Constants.screenSize.y / (levels.size() + 1);
        for ( Button button : levels ) {
            addObject(button, Constants.screenSize.x / 2, y);
            y += Constants.screenSize.y / (levels.size() + 1);
        }

    }

    private void addLevels() {

        levels.add(new Button("Level One", () -> {
                Level level = new Level("one");
                Greenfoot.setWorld(level);
            }));
        levels.add(new Button("Level Two", () -> {
            Level level = new Level("two");
            Greenfoot.setWorld(level);
        }));
        levels.add(new Button("Level Three", () -> {
            Level level = new Level("three");
            Greenfoot.setWorld(level);
        }));
    }
}
