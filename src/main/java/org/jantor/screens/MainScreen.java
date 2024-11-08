package org.jantor.screens;

import org.jantor.widgets.Button;
import org.jantor.levels.Level;

public class MainScreen extends Screen {

    private final Button[] buttons = {
            new Button("One", new Level("one.json"))
    };

    public MainScreen() {
        super();

        setBackground();
        loadOptions();
        act();
    }

    private void loadOptions() {
        Layout layout = new Layout(buttons, 10, 10, 0, 0, 0, 250);
        layout.addTo(this);
    }
}
