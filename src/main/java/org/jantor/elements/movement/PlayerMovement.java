package org.jantor.elements.movement;

import org.jantor.elements.Player;
import java.util.Arrays;

public class PlayerMovement extends Movement {

    public PlayerMovement(Player player) {
        super(player);
    }

    @Override
    public void act() {
        lastDirection.add(currentDirection);
        currentDirection.toZero();
        Arrays.stream(MovementDirection.values()).forEach(direction -> {
            if (direction.isPressed()) currentDirection.add(direction.vector);
        });

        if (MovementDirection.UP.isPressed() && onGround) {
            verticalMomentum = -jumpStrength;
            onGround = false;
        }

        super.act();
    }
}