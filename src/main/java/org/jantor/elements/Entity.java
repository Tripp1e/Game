package org.jantor.elements;

import greenfoot.Actor;
import org.jantor.elements.movement.Movement;
import org.jantor.utils.GifImage;
import org.jantor.utils.GreenfootImage;

public class Entity extends Element {

    Movement movement;
    final public int speed;
    public int score = 0;

    public enum EntityImage {
        WALKING("gif"),
        JUMPING("png"),
        CROUCHING("png"),
        STANDING("png");

        final String type;
        final GifImage gif;

        EntityImage(String type) {
            this.type = type;
            this.gif = new GifImage(filePath());
        }

        private String filePath() {
            return "player/" + name().toLowerCase() + "." + type;
        }

        public GreenfootImage getCurrentImage() {
            return gif.getCurrentImage();
        }

        public GreenfootImage getCurrentImageMirrored() {
            return gif.getCurrentImage().mirror();
        }
    }

    public Entity(Movement movement, int speed) {
        super(EntityImage.WALKING.gif.getCurrentImage());

        this.speed = speed;
        this.movement = movement;
    }

    public Entity(Movement movement) {
        this(movement, 10);
    }

    @Override
    public void act() {
        movement.act();
    }

    @Override
    public Actor getOneObjectAtOffset(int dx, int dy, Class<?> cls) {
        return super.getOneObjectAtOffset(dx, dy, cls);
    }

}