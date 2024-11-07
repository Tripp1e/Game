import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Level extends World {

    private void setBackground() {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLUE);
        background.fill();
    }

    public Level(int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight, 1);
        setBackground();
    }

}