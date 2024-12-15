package org.jantor.screens;

import greenfoot.Color;
import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.level.Level;
import org.jantor.ui.Button;
import org.jantor.utils.GreenfootImage;

public class Main extends Screen {

    Button levelSelection = new Button("Levels", () -> {
        LevelSelection levelSelection = new LevelSelection();
        Greenfoot.setWorld(levelSelection);
    });

    Button options = new Button("Options", () -> {
        Options optionScreen = new Options();
        Greenfoot.setWorld(optionScreen);
    });

    Button start = new Button("Start!", () -> {
        Level level = new Level("one");
        Greenfoot.setWorld(level);
    });

    GreenfootImage background = new GreenfootImage("ui/mainBackground.png");
    //GreenfootImage knightMain = new GreenfootImage("ui/mainKnight.png");

    public Main() {
        super();
        addObject(start, 200, Constants.screenSize.y / 2 - 100);
        addObject(levelSelection, 200, Constants.screenSize.y / 2 );
        addObject(options, 200, Constants.screenSize.y / 2 + 100);
        setBackground(background.mirror());
    }
}
