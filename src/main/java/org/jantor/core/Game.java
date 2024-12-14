package org.jantor.core;

import org.jantor.screens.Screen;
import org.jantor.ui.Button;
import org.jantor.level.Level;

public class Game extends Screen {

    private final Button[] buttons = {
            new Button("One", new Level("one"))
    };

    public Game() {
        super();

        loadOptions();
        act();
    }

    private void loadOptions() {
        addObject(buttons[0], getHeight() / 2, getHeight() / 2);
    }
}
