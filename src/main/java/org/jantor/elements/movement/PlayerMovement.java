package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.constants.PlayerInfo;
import org.jantor.elements.Collectable;
import org.jantor.elements.Player;
import org.jantor.shop.Shop;
import org.jantor.utils.Vector2D;

import java.util.Arrays;

public class PlayerMovement extends EntityMovement {

    public PlayerMovement(Player player) {
        super(player, (int) PlayerInfo.get("speed"), (int) PlayerInfo.get("jumpStrength"));
    }

    @Override
    public void act() {
        if (entity == null) return;
        latestDirection.add(currentDirection).normalize();
        lastDirection.copy(currentDirection);
        currentDirection.toZero();

        ((Player) entity).collect(Collectable.class);

        Arrays.stream(Movement.Direction.values()).forEach(direction -> {
            if (Greenfoot.isKeyDown(direction.name().toLowerCase())) currentDirection.add(direction.vector).normalize();
        });

        if (Greenfoot.isKeyDown(Movement.Direction.UP.name().toLowerCase())) {
            if (onGround) {
                verticalMomentum = -getJumpStrength();
                onGround = false;
            } else if (!doubleJumped) {
                System.out.println("Double jumped!");
                verticalMomentum = -getJumpStrength();
                doubleJumped = true;
            }
        }

        boolean isNearLeftEdge = entity.getX() < Constants.screenSize.x * 0.25;
        boolean isNearRightEdge = entity.getX() > Constants.screenSize.x * 0.75;

        int screenOffsetX =
                isNearLeftEdge && currentDirection.x == Direction.LEFT.vector.x
                ? -1 * getSpeed()

                : (isNearRightEdge && currentDirection.x == Direction.RIGHT.vector.x
                ? getSpeed()

                : 0);
        Constants.renderer.updateBlocks();
        Constants.originOffset.add(new Vector2D(-screenOffsetX, 0));

        if( !entity.getWorld().getObjects(Shop.class).isEmpty() ) currentDirection.toZero();

        super.act();
    }

    @Override
    public int getSpeed()                           { return (int) PlayerInfo.get("speed"); }
    @Override
    public void setSpeed(int speed)                 { PlayerInfo.set("speed", speed); }
    @Override
    public int getJumpStrength()                    { return (int) PlayerInfo.get("jumpStrength"); }
    @Override
    public void setJumpStrength(int jumpStrength)   { PlayerInfo.set("jumpStrength", jumpStrength); }
}