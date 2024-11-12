package org.jantor.level.element.entity;

import org.jantor.image.PlayerGifImage;
import org.jantor.image.PlayerImage;
import org.jantor.level.element.collectable.Collectable;
import org.jantor.level.element.entity.movement.PlayerMovement;

public class Player extends Entity {
    final static PlayerGifImage walkGif = new PlayerGifImage("player/walk.gif");
    final static PlayerGifImage walkMirrorGif = new PlayerGifImage("player/walkMirror.gif");
    final static PlayerImage jumpImg = new PlayerImage("jumping.png");
    final static PlayerImage crouchImg = new PlayerImage("crouching.png");

    public int score = 0;

    public Player() {
        super(null,
                walkGif,
                walkMirrorGif,
                jumpImg,
                crouchImg);
        this.movement = new PlayerMovement(this);
    }

    public boolean canCollectAny(Class<? extends Collectable> cls) {
        return isTouching(cls);
    }

    public void collect(Class<? extends Collectable> cls) {
        score+=1;
        super.removeTouching(cls);
    }
}
