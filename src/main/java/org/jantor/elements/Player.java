package org.jantor.elements;

import greenfoot.Actor;
import org.jantor.constants.Constants;
import org.jantor.elements.movement.PlayerMovement;
import org.jantor.utils.Vector2D;

import java.util.Collection;

public class Player extends Entity {

    public int score = 0;

    public Player(Vector2D pos) {
        super(null, 5, pos);
        this.movement = new PlayerMovement(this);
    }

    public void collect(Class<? extends Collectable> cls) {
        if (!isTouching(cls)) return;
        score += 1;
        Collectable collectable = (Collectable) getOneIntersectingObject(cls);
        Constants.renderer.collectables.remove(collectable);
        getWorld().removeObject(collectable);
    }

}
