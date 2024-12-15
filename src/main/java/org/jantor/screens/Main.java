package org.jantor.screens;

import org.jantor.constants.Constants;
import org.jantor.ui.Button;
import org.jantor.utils.GreenfootImage;
import org.jantor.ui.Button.ButtonType;

public class Main extends Screen {

    Button levelSelection = new Button("Levels", ButtonType.LEVELSELECTION);
    Button options =        new Button("Options", ButtonType.OPTIONS);
    Button start =          new Button("Start!", ButtonType.LEVEL, "one");

    GreenfootImage background = new GreenfootImage("ui/mainBackground.png");
    //GreenfootImage knightMain = new GreenfootImage("ui/mainKnight.png");

    public Main() {
        super();
        setBackground(background.mirror());
        addObject(start, 200, Constants.screenSize.y / 2 - 100);
        addObject(levelSelection, 200, Constants.screenSize.y / 2 );
        addObject(options, 200, Constants.screenSize.y / 2 + 100);
    }
}
