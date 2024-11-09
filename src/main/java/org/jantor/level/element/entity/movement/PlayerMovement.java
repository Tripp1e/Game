package org.jantor.level.element.entity.movement;

import greenfoot.Greenfoot;
import org.jantor.level.element.entity.Player;

public class PlayerMovement extends Movement {
    public PlayerMovement(Player player) {
        super(player);
    }

    void move() {

    }

    @Override
    public void act() {
        movements.forEach((direction, action) -> {
            if (Greenfoot.isKeyDown(direction.name().toLowerCase())) {
                action.run();
            }
        });
    }
}
