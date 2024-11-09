package org.jantor.level.element.entity;

import org.jantor.level.element.entity.movement.PlayerMovement;

public class Player extends Entity {
    final static String walkImgPath = "player/walking.gif";
    final static String jumpImgPath = "player/jumping.png";
    final static String crouchedImgPath = "player/crouching.png";

    public Player() {
        super(crouchedImgPath, null,
                walkImgPath,
                jumpImgPath,
                crouchedImgPath);
        this.movement = new PlayerMovement(this);
    }
}
