package org.jantor.elements;

import greenfoot.Greenfoot;
import org.jantor.screens.Death;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

public class Block extends Element {
    BlockType type;

    public enum BlockType {
        DIRT,
        GRASS,
        STONE,
        SAND,
        DEATHBLOCK;

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

    public Block(BlockType type, Vector2D position) {
        super(type.getImage(), position);
        this.type = type;
    }

    public void act() {
        if (type == BlockType.DEATHBLOCK) {
            if (isTouching(Player.class)) {
                Greenfoot.setWorld(new Death("You touched the Death Block!"));
            }
        }
    }

}
