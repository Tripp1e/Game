package org.jantor.level.element.entity.movement;

import org.jantor.image.GreenfootImage;
import org.jantor.level.element.block.Block;
import org.jantor.level.element.entity.Entity;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class Movement {

    public final Entity entity;
    protected enum Direction { LEFT, RIGHT, UP, DOWN }
    protected static final Map<Direction, Runnable> movements = new EnumMap<>(Direction.class);

    private boolean facingLeft;

    public Movement(Entity entity) {
        this.entity = entity;
        initializeMovements();
    }

    private void initializeMovements() {
        movements.put(Direction.LEFT, this::moveLeft);
        movements.put(Direction.RIGHT, this::moveRight);
        movements.put(Direction.UP, this::moveUp);
        movements.put(Direction.DOWN, this::moveDown);
    }

    public abstract void act();

    private void moveEntity(int dx, int dy, boolean facingLeft, GreenfootImage image) {
        entity.setImage(image);
        if (cantMoveTo(dx, dy)) return;
        entity.setLocation(entity.getX() + dx, entity.getY() + dy);
        this.facingLeft = facingLeft;
    }
    private void moveLeft()     { moveEntity(-entity.speed, 0, true, entity.walking); }
    private void moveRight()    { moveEntity( entity.speed, 0, false, entity.mirroredWalking); }
    private void moveDown()     { moveEntity(0,  entity.speed, facingLeft, entity.crouching); }
    private void moveUp()       { moveEntity(0, -entity.speed, facingLeft, facingLeft ? entity.jumping : entity.mirroredJumping); }

    protected boolean cantMoveTo(int dx, int dy) {
        int halfWidth = Block.width / 2;
        int[][] offsets = {
                {-halfWidth, -halfWidth},
                { halfWidth, -halfWidth},
                {-halfWidth,  halfWidth},
                { halfWidth,  halfWidth}
        };
        return Arrays.stream(offsets)
                .anyMatch(offset -> entity.getOneObjectAtOffset(dx + offset[0], dy + offset[1], Block.class) != null);
    }

}
