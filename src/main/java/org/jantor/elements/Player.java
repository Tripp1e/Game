package org.jantor.elements;

import org.jantor.constants.Constants;
import org.jantor.constants.PlayerInfo;
import org.jantor.elements.movement.PlayerMovement;
import org.jantor.utils.Vector2D;

public class Player extends Entity {

    public Player(Vector2D pos) {
        super(null, pos);
        initInfoEntries();
        movement = new PlayerMovement(this);
    }

    void initInfoEntries() {
        PlayerInfo.setInitial("coin", 0);
        PlayerInfo.setInitial("star", 0);
        PlayerInfo.setInitial("speed", 5);
        PlayerInfo.setInitial("jumpStrength", 10);
    }

    public void collect(Class<? extends Collectable> cls) {
        if (!isTouching(cls)) return;
        Collectable collectable = (Collectable) getOneIntersectingObject(cls);
        String key = collectable.type.toString();

        System.out.println("Collecting " + key);

        int amount = (int) PlayerInfo.get(key, 0);
        amount++;
        PlayerInfo.set(key, amount);

        Constants.updateCounters();

        Constants.renderer.collectables.remove(collectable);
        getWorld().removeObject(collectable);
    }
}