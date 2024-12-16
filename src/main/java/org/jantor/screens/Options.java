package org.jantor.screens;

import org.jantor.constants.Constants;
import org.jantor.constants.Keybinds;
import org.jantor.ui.Button;

import java.util.ArrayList;

public class Options extends Screen {

    ArrayList<Button> buttons = new ArrayList<>();

    public Options() {
        initKeyBindings();

        int y = Constants.screenSize.y / (Keybinds.keybinds.size() + 1);
        for (Button button : buttons) {
            addObject(button, Constants.screenSize.x / 2, y);
            y += Constants.screenSize.y / (Keybinds.keybinds.size() + 1);
        }
    }

    private void initKeyBindings() {
        for ( String keybind : Keybinds.keybinds.keySet()) {
            buttons.add(new Button(keybind.toUpperCase() + ": " + Keybinds.keybinds.get(keybind), Button.ButtonType.GENERIC, () -> Keybinds.setKeybind(keybind)));
        }
    }
}
