package org.jantor.screen;

import org.jantor.level.LevelBuilder;
import org.jantor.level.LevelSerializer;
import org.jantor.widget.Button;
import org.jantor.level.Level;
import org.jantor.widget.Clickable;

import javax.swing.*;

public class MainScreen extends Screen {

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
            new Button("One", new Level("one.json")),
            new Button("Two", new Level("two.json")),
            new Clickable("LevelBuilder", runnable)
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
