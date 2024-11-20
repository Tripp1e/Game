package org.jantor.elements;

import org.jantor.constants.Constants;
import org.jantor.elements.movement.BlockMovement;
import org.jantor.elements.movement.Movement;
import org.jantor.utils.GreenfootImage;

public class Block extends Element {

    public enum BlockType {
        DIRT,
        GRASS,
        STONE,
        SAND;

        private String filePath() {
            return "images/blocks/" + name().toLowerCase() + ".png";
        }

        public GreenfootImage getImage() {
            return new GreenfootImage(filePath());
        }

        public static BlockType getByString(String name) {
            try {
                return BlockType.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    private BlockMovement movement;

    public Block(BlockType type) {
        super(type.getImage());
        this.movement = new BlockMovement(this);
    }

    public void act() {
        movement.act();
    }

}
