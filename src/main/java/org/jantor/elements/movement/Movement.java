package org.jantor.elements.movement;

import greenfoot.Greenfoot;
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
    protected Vector2D lastDirection;

    protected boolean onGround = false;
    protected int verticalSpeed = 0;
    int gravity = 1;
    int jumpStrength = 20;


    public Movement(Entity entity) {
        this.entity = entity;
        this.currentDirection = new Vector2D(0, 0);
        this.lastDirection = new Vector2D(0, 0);
    }

    public void act() {
        entity.setImage(isFacingLeft() ? image().getCurrentImage() : image().getCurrentImageMirrored());
        applyGravity();
        updateLocation();
    }

    private void updateLocation() {
        int dx = entity.speed * currentDirection.x;
        int dy = onGround ? jumpStrength * currentDirection.y : verticalSpeed;

        if (cantMoveTo(dx, 0)) dx = 0;
        if (cantMoveTo(0, dy)) dy = 0;

        entity.setLocation(entity.getX() + dx, entity.getY() + dy);
    }

    protected void applyGravity() {
        if (onGround) {
            onGround = cantMoveTo(0, 1);  // Check if the entity is still on the ground
            if (onGround) return;
        }

        verticalSpeed += gravity;

        if (cantMoveTo(0, verticalSpeed)) {
            while (cantMoveTo(0, verticalSpeed) && verticalSpeed != 0) {
                verticalSpeed--;
            }

            onGround = cantMoveTo(0, 1);

            verticalSpeed = 0;
        } else {
            onGround = false;
        }
    }



    protected boolean cantMoveTo(int dx, int dy) {
        int[][] offsets = {
                {-25, -25}, { 25, -25}, {-25,  25}, { 25,  25}
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
        if (!onGround) return EntityImage.JUMPING;
        if (currentDirection.y == 1) return EntityImage.CROUCHING;
        if (currentDirection == Vector2D.zeroVector) return EntityImage.STANDING;
        return EntityImage.WALKING;
    }
    private boolean isFacingLeft() { return lastDirection.x == -1; }

}
