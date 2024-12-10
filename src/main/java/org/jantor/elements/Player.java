package org.jantor.elements;

import org.jantor.elements.movement.PlayerMovement;
import org.jantor.utils.Vector2D;

public class Player extends Entity {

    public int score = 0;

    public Player(Vector2D pos) {
        super(null, 5, pos);
        this.movement = new PlayerMovement(this);
    }

    public void collect(Class<? extends Collectable> cls) {
        if (!isTouching(cls)) return;
        score += 1;
        System.out.println("Collecting " + cls.getSimpleName());
        System.out.println("Score: " + score);
        super.removeTouching(cls);
    }

}
