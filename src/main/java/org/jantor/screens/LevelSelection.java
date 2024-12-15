package org.jantor.screens;

import org.jantor.constants.Constants;
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

        levels.add(new Button("Level One", Button.ButtonType.LEVEL, "one"));
        levels.add(new Button("Level Two", Button.ButtonType.LEVEL, "two"));
        levels.add(new Button("Level Three", Button.ButtonType.LEVEL, "three"));

    }
}
