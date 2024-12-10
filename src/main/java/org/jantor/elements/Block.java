package org.jantor.elements;

import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

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

    public Block(BlockType type, Vector2D position) {
        super(type.getImage(), position);
    }

}
