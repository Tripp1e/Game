package org.jantor.level.element.entity;

import org.jantor.image.PlayerImage;
import org.jantor.level.element.entity.movement.PlayerMovement;

public class Player extends Entity {
    final static PlayerImage walkImg = new PlayerImage("walking.gif");
    final static PlayerImage jumpImg = new PlayerImage("jumping.png");
    final static PlayerImage crouchImg = new PlayerImage("crouching.png");

    public Player() {
        super(null,
                walkImg,
                jumpImg,
                crouchImg);
        this.movement = new PlayerMovement(this);
    }

}
