package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.elements.Collectable;
import org.jantor.elements.Player;

import java.util.Arrays;

public class PlayerMovement extends EntityMovement {

    public PlayerMovement(Player player) {
        super(player);
    }

    @Override
    public void act() {
        latestDirection.add(currentDirection);
        lastDirection.copy(currentDirection);
        currentDirection.toZero();

        ((Player) entity).collect(Collectable.class);

        Arrays.stream(Movement.Direction.values()).forEach(direction -> {
            if (Greenfoot.isKeyDown(direction.name().toLowerCase())) currentDirection.add(direction.vector);
        });

        if (Greenfoot.isKeyDown(Movement.Direction.UP.name().toLowerCase()) && onGround) {
            verticalMomentum = -jumpStrength;
            onGround = false;
        }

        super.act();
    }
}