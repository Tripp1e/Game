package org.jantor.elements;

import org.jantor.utils.GreenfootImage;

public class Collectable extends Element {
    public static int width = 50;
    public static int height = 50;

    public enum CollectableType {
        COIN;

        private String filePath() {
            return "images/blocks/" + name();
        }

        public GreenfootImage getImage() {
            return new GreenfootImage(filePath());
        }
    }

    public Collectable(CollectableType type) {
        super( type.getImage() );
    }

}