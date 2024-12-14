package org.jantor.screens;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.ui.Button;

public class Main extends Screen {

    Button levelSelection = new Button("Levels", () -> {
        LevelSelection levelSelection = new LevelSelection();
        Greenfoot.setWorld(levelSelection);
    });

    Button options = new Button("Options", () -> {
        Options optionScreen = new Options();
        Greenfoot.setWorld(optionScreen);
    });

    public Main() {
        super();
        addObject(options, 200, Constants.screenSize.y / 2 + 100);
        addObject(levelSelection, 200, Constants.screenSize.y / 2 - 100);
    }
}
