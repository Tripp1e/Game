package org.jantor.screens;

import org.jantor.constants.Constants;
import org.jantor.ui.Button;
import org.jantor.utils.GreenfootImage;
import org.jantor.ui.Button.ButtonType;

import java.util.Objects;

public class Main extends Screen {

    Button levelSelection = new Button("Levels", ButtonType.LEVELSELECTION);
    Button options = new Button("Options", ButtonType.OPTIONS);

    String currentLevel = Constants.getCurrentLevel();

    Button start = new Button(Objects.equals(currentLevel, "one") ? "Start!" : "Continue: " + currentLevel, ButtonType.LEVEL, Constants.getCurrentLevel());

    public Main() {
        super();
        addObject(start, 200, Constants.screenSize.y / 2 - 100);
        addObject(levelSelection, 200, Constants.screenSize.y / 2 );
        addObject(options, 200, Constants.screenSize.y / 2 + 100);
    }
}
