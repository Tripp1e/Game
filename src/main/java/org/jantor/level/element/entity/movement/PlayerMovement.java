package org.jantor.level.element.entity.movement;

import greenfoot.Greenfoot;
import org.jantor.level.element.collectable.Collectable;
import org.jantor.level.element.entity.Player;

public class PlayerMovement extends Movement {
    Player player;
    public PlayerMovement(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public void act() {
        applyGravity();
        player.collect(Collectable.class);
        movements.forEach((direction, action) -> {
            if (Greenfoot.isKeyDown(direction.name().toLowerCase())) {
                action.run();
            }
        });
    }
}