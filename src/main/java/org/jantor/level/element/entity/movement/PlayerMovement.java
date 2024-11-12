package org.jantor.level.element.entity.movement;

import greenfoot.Greenfoot;
import org.jantor.level.element.entity.Player;

public class PlayerMovement extends Movement {
    public PlayerMovement(Player player) {
        super(player);
    }

    @Override
    public void act() {
        applyGravity();
        movements.forEach((direction, action) -> {
            if (Greenfoot.isKeyDown(direction.name().toLowerCase())) {
                action.run();
            }
        });
    }

    public void checkCollectable() {
        if (entity.to)
    }
}