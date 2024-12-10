package org.jantor.core;

import org.jantor.level.LevelBuilder;
import org.jantor.level.LevelSerializer;
import org.jantor.screens.Screen;
import org.jantor.ui.Button;
import org.jantor.level.Level;
import org.jantor.ui.Clickable;

import javax.swing.*;

public class Game extends Screen {

    private final Runnable runnable = () -> {
        String failName = JOptionPane.showInputDialog("Whats the file name?");

        LevelBuilder levelBuilder = LevelBuilder.createLevel(25, 17);
        levelBuilder.setBlock(1, 1, "stone")
                .setBlock(2, 1, "grass")
                .setBlock(5, 5, "dirt")
                .setBlock(6, 6, "player")
                .setRow(14, "dirt");
        String[][] generatedLevel = levelBuilder.build();

        LevelSerializer.saveToFile(generatedLevel, failName);
    };

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
