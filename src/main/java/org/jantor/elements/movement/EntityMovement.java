package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Entity;
import org.jantor.elements.Entity.EntityImage;
import org.jantor.utils.CollisionManager;

public class EntityMovement extends Movement {
    protected Entity entity;

    protected boolean onGround = false;
    private boolean hasJumped = false;
    protected int verticalMomentum = 0;
    int gravity = 1;
    public int jumpStrength;
    public int speed;


    public EntityMovement(Entity entity, int speed, int jumpStrength) {
        super();
        this.entity = entity;
        setJumpStrength(jumpStrength);
        setSpeed(speed);
    }

    public void act() {
        applyGravity();
        updateLocation();
        if (entity == null) return;
        entity.setImage(isFacingLeft() ? image().getCurrentImage() : image().getCurrentImageMirrored());
        //applySounds();
    }

    private void updateLocation() {
        if (entity == null) return;
        int dx = getSpeed() * currentDirection.x;
        int dy = onGround ? getJumpStrength() * currentDirection.y : verticalMomentum;

        if (wouldCollide(dx, 0)) dx = 0;
        if (wouldCollide(0, dy)) dy = 0;

        entity.setLocation(entity.getX() + dx, entity.getY() + dy);
    }

    private void applyGravity() {
        if (onGround) {
            onGround = wouldCollide(0, 1);
            if (onGround) return;
        }

        verticalMomentum += gravity;

        if (wouldCollide(0, verticalMomentum)) {
            while (wouldCollide(0, verticalMomentum) && verticalMomentum != 0) {
                verticalMomentum--;
            }

            onGround = wouldCollide(0, 1);

            verticalMomentum = 0;
        } else {
            onGround = false;
        }
    }

    private boolean wouldCollide(int dx, int dy) { return CollisionManager.wouldCollide(dx, dy, Block.class, entity); }

    private void applySounds() {
        int leftY = Direction.LEFT.vector.y;

        boolean playJumped = currentDirection.y == leftY && lastDirection.y == 0 && hasJumped;
        if (playJumped) Greenfoot.playSound("sounds/jumped.wav");
        hasJumped = onGround;
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

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setJumpStrength(int jumpStrength) { this.jumpStrength = jumpStrength; }
    public int getJumpStrength() { return jumpStrength; }

}