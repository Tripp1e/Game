package org.jantor.elements;

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

    public Block(BlockType type) {
        super( type.getImage() );
    }

}
