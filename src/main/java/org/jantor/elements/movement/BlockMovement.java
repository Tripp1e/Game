package org.jantor.elements.movement;

import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Border;

public class BlockMovement extends Movement {
    protected Block block;

    public BlockMovement(Block block) {
        super();
        this.block = block;
    }

    @Override
    public void act() {
        if (!Constants.player.isTouching(Border.class)) return;
        if (block.isAtEdge()) block.getWorld().removeObject(block);
        block.move(Constants.player.speed * -1 * Constants.player.movement.currentDirection.x);

    }
}
