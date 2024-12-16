package org.jantor.elements.movement;

import org.jantor.constants.Keybinds;
import org.jantor.utils.Vector2D;

import java.security.Key;

public abstract class Movement {
    protected enum Direction {
        LEFT(new Vector2D(-1, 0)),
        RIGHT(new Vector2D(1, 0)),
        UP(new Vector2D(0, -1)),
        DOWN(new Vector2D(0, 1));

        public final Vector2D vector;

        Direction(Vector2D vector) {
            this.vector = vector;
        }

        public String getKeybind() {
            return Keybinds.get(name().toLowerCase());
        }
    }

    public Vector2D currentDirection;
    protected Vector2D latestDirection;
    protected Vector2D lastDirection;

    public Movement() {
        this.currentDirection = new Vector2D(0, 0);
        this.lastDirection = new Vector2D(0, 0);
        this.latestDirection = new Vector2D(0, 0);
    }

    public abstract void act();

}