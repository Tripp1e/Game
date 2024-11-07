import greenfoot.*;

public class Option extends Actor {
    String name;
    World option;

    public Option(String name, World option) {
        this.name = name;
        this.option = option;
        setHitbox();
    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            System.out.println("Mouse was clicked!");
            Greenfoot.setWorld(option);
        }
    }

    private void setHitbox() {
        GreenfootImage image = new GreenfootImage(200, 50);  // Set the size as desired
        image.setColor(Color.BLACK);
        image.fill();

        image.setColor(Color.WHITE);
        image.drawString(name, 10, 25);

        setImage(image);
    }

}
