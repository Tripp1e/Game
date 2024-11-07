import greenfoot.*;

public class GameWorld extends World {

    private final Option[] options = {
            new Option("Level 1", new Level(getWidth(), getHeight()))
    };

    public GameWorld() {
        super(600, 400, 1);

        setBackground();
        loadOptions();
    }


    private void loadOptions() {
        for (Option option : options) addObject(option, getWidth() / 2, getHeight() / 2);
    }

    private void setBackground() {
        GreenfootImage background = getBackground();

        background.setColor(Color.WHITE);
        background.fill();
    }

}