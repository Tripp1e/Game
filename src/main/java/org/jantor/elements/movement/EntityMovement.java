package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Entity;
import org.jantor.elements.Entity.EntityImage;
import org.jantor.utils.Vector2D;
import org.reactfx.util.LL;

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
        Vector2D delta = new Vector2D(dx, dy);
        if (wouldCollide(delta)) delta.toZero();

        entity.setLocation(entity.getX() + dx, entity.getY() + dy);
    }

    private void applyGravity() {
        if (onGround) {
            onGround = wouldCollide(Direction.DOWN);
            if (onGround) return;
        }

        verticalMomentum += gravity;

        if (wouldCollide(Direction.DOWN, verticalMomentum)) {
            while (wouldCollide(Direction.DOWN.vector.multiply(verticalMomentum)) && verticalMomentum != 0) verticalMomentum--;
            onGround = wouldCollide(Direction.DOWN);
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

    boolean wouldCollide(int dx, int dy) {
        int halfWidth = Constants.elementSize.x / 2;
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
    boolean wouldCollide(Vector2D vector) { return wouldCollide(vector.x, vector.y); };

    boolean wouldCollide(Direction direction, int multiplier) {
        return wouldCollide(new Vector2D(direction.vector).multiply(multiplier));
    }
    boolean wouldCollide(Direction direction) {
        return wouldCollide(direction, 1);
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