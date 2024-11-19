package org.jantor.elements;

import org.jantor.utils.GreenfootImage;

public class Collectable extends Element {

    public enum CollectableType {
        COIN;

        private String filePath() {
            return "images/collectables/" + name().toLowerCase() + ".png";
        }

        public GreenfootImage getImage() {
            return new GreenfootImage(filePath());
        }
    }

    public Collectable(CollectableType type) {
        super(type.getImage());
    }

}