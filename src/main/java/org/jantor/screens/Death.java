package org.jantor.screens;

import org.jantor.constants.Constants;
import org.jantor.ui.Button;

public class Death extends Screen {

    Button deathMessage = new Button("You died lmao", Button.ButtonType.TEXT, "");
    Button reset = new Button("Reset", Button.ButtonType.LEVEL, "one");

    public Death() {
        addObject(reset, Constants.screenSize.x / 2, Constants.screenSize.y / 2);
    }

}
