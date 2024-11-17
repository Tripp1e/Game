package org.jantor.elements;

import greenfoot.Actor;
import org.jantor.utils.GifImage;
import org.jantor.elements.movement.Movement;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

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

        EntityImage(String type) {
            this.type = type;
        }

        private String filePath() {
            return "player/" + name().toLowerCase() + "." + type;
        }

        private GifImage getGif() {
            return new GifImage(filePath());
        }

        public GreenfootImage getCurrentImage() {
            return getGif().getCurrentImage();
        }

        public GreenfootImage getCurrentImageMirrored() {
            return getGif().getCurrentImage().mirror();
        }
    }

    public Entity(Movement movement, int speed) {
        super(EntityImage.WALKING.getGif().getCurrentImage());

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