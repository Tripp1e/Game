package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Entity;
import org.jantor.utils.Vector2D;
import org.jantor.elements.Entity.EntityImage;

public class EntityMovement extends Movement {
    protected Entity entity;

    protected boolean onGround = false;
    private boolean hasJumped = false;
    protected int verticalMomentum = 0;
    int gravity = 1;
    int jumpStrength = 20;


    public EntityMovement(Entity entity) {
        super();
        this.entity = entity;
    }

    public void act() {
        applyGravity();
        updateLocation();
        entity.setImage(isFacingLeft() ? image().getCurrentImage() : image().getCurrentImageMirrored());
        //applySounds();
    }

    private void updateLocation() {
        int dx = entity.speed * currentDirection.x;
        int dy = onGround ? jumpStrength * currentDirection.y : verticalMomentum;

        if (cantMoveTo(dx, 0)) dx = 0;
        if (cantMoveTo(0, dy)) dy = 0;

        entity.setLocation(entity.getX() + dx, entity.getY() + dy);
    }

    private void applyGravity() {
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
        int leftY = Direction.LEFT.vector.y;

        boolean playJumped = currentDirection.y == leftY && lastDirection.y == 0 && hasJumped;
        if (playJumped) Greenfoot.playSound("sounds/jumped.wav");
        hasJumped = onGround;
    }


    private boolean cantMoveTo(int dx, int dy) {
        int halfWidth = Constants.elementWidth / 2;
        int[][] offsets = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

        for (int[] offset : offsets) {
            int newX = entity.getX() + offset[0] * halfWidth + dx;
            int newY = entity.getY() + offset[1] * halfWidth + dy;
            if (entity.getOneObjectAtOffset(newX - entity.getX(), newY - entity.getY(), Block.class) != null) {
                return true;
            }
        }
        return false;
    }

    private EntityImage image() {
        if (!onGround) return EntityImage.JUMPING;
        if (currentDirection.y == 1) return EntityImage.CROUCHING;
        if (currentDirection.equals(Constants.zeroVector)) return EntityImage.STANDING;
        return EntityImage.WALKING;
    }

    private boolean isFacingLeft() {
        return latestDirection.x == -1;
    }

}
