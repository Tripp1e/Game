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
        PlayerInfo.setInitial("coins", 0);
        PlayerInfo.setInitial("speed", 5);
        PlayerInfo.setInitial("jumpStrength", 10);
    }

    public void collect(Class<? extends Collectable> cls) {
        if (!isTouching(cls)) return;
        int coins = (int) PlayerInfo.data.getOrDefault("coins", 0);
        PlayerInfo.data.put("coins", coins + 1);

        Collectable collectable = (Collectable) getOneIntersectingObject(cls);
        Constants.renderer.collectables.remove(collectable);
        getWorld().removeObject(collectable);

        Constants.coinCounter.setCounter((int) PlayerInfo.data.get("coins"));

        if (coins + 1 == Constants.coinAmount) System.out.println("You completed the Game!");
    }

}
