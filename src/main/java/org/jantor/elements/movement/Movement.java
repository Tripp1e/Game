package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Entity;
import org.jantor.utils.Vector2D;
import org.jantor.elements.Entity.EntityImage;

public abstract class Movement {
    protected enum MovementDirection {
        LEFT(new Vector2D(-1, 0)),
        RIGHT(new Vector2D(1, 0)),
        UP(new Vector2D(0, -1)),
        DOWN(new Vector2D(0, 1));

        public final Vector2D vector;

        MovementDirection(Vector2D vector) {
            this.vector = vector;
        }

        public boolean isPressed() {
            return Greenfoot.isKeyDown(this.name().toLowerCase());
        }

    }

    private final Entity entity;
    protected Vector2D currentDirection;
    protected Vector2D latestDirection;
    protected Vector2D lastDirection;

    protected boolean onGround = false;
    private boolean hasJumped = false;
    protected int verticalMomentum = 0;
    int gravity = 1;
    int jumpStrength = 20;


    public Movement(Entity entity) {
        this.entity = entity;
        this.currentDirection = new Vector2D(0, 0);
        this.lastDirection = new Vector2D(0, 0);
        this.latestDirection = new Vector2D(0, 0);
    }

    public void act() {
        entity.setImage(isFacingLeft() ? image().getCurrentImage() : image().getCurrentImageMirrored());
        applyGravity();
        updateLocation();
        applySounds();
    }

    private void updateLocation() {
        int dx = entity.speed * currentDirection.x;
        int dy = onGround ? jumpStrength * currentDirection.y : verticalMomentum;

        if (cantMoveTo(dx, 0)) dx = 0;
        if (cantMoveTo(0, dy)) dy = 0;

        entity.setLocation(entity.getX() + dx, entity.getY() + dy);
    }

    protected void applyGravity() {
        if (onGround) {
            onGround = cantMoveTo(0, 1);
            if (onGround) return;
        }

        verticalMomentum += gravity;

        if (cantMoveTo(0, verticalMomentum)) {
            while (cantMoveTo(0, verticalMomentum) && verticalMomentum != 0) {
                verticalMomentum--;
            }

            onGround = cantMoveTo(0, 1);

            verticalMomentum = 0;
        } else {
            onGround = false;
        }
    }

    private void applySounds() {
        boolean playJumped = currentDirection.y == -1 && lastDirection.y == 0 && hasJumped;
        if (playJumped) Greenfoot.playSound("sounds/jumped.wav");
        hasJumped = onGround;

    }


    protected boolean cantMoveTo(int dx, int dy) {
        int[][] offsets = {
                {-25, -25}, {25, -25}, {-25, 25}, {25, 25}
        };

        for (int[] offset : offsets) {
            int newX = entity.getX() + offset[0] + dx;
            int newY = entity.getY() + offset[1] + dy;
            if (entity.getOneObjectAtOffset(newX - entity.getX(), newY - entity.getY(), Block.class) != null) {
                return true;
            }
        }
        return false;
    }

    private EntityImage image() {
        if (!onGround)                                      return EntityImage.JUMPING;
        if (currentDirection.y == 1)                        return EntityImage.CROUCHING;
        if (currentDirection.equals(Constants.zeroVector))  return EntityImage.STANDING;
        return EntityImage.WALKING;
    }

    private boolean isFacingLeft() {
        return latestDirection.x == -1;
    }

}
