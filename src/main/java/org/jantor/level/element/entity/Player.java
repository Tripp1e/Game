package org.jantor.level.element.entity;

import org.jantor.image.PlayerGifImage;
import org.jantor.image.PlayerImage;
import org.jantor.level.element.entity.movement.PlayerMovement;

public class Player extends Entity {
    final static PlayerGifImage walkGif = new PlayerGifImage("player/walk.gif");
    final static PlayerGifImage walkMirrorGif = new PlayerGifImage("player/walkMirror.gif");
    final static PlayerImage jumpImg = new PlayerImage("jumping.png");
    final static PlayerImage crouchImg = new PlayerImage("crouching.png");

    public Player() {
        super(null,
                walkGif,
                walkMirrorGif,
                jumpImg,
                crouchImg);
        this.movement = new PlayerMovement(this);
    }

}