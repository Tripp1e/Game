package org.jantor.level.element.entity;

import org.jantor.image.PlayerImage;
import org.jantor.level.element.entity.movement.PlayerMovement;

public class Player extends Entity {
    final static PlayerImage walkImg = new PlayerImage("walking.gif");
    final static PlayerImage jumpImg = new PlayerImage("jumping.png");
    final static PlayerImage crouchedImg = new PlayerImage("crouching.png");

    public Player() {
        super(null, walkImg, jumpImg, crouchedImg);
        this.movement = new PlayerMovement(this);
    }
}
