package org.jantor.elements;

import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

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

    public Collectable(CollectableType type, Vector2D position) {
        super(type.getImage(), position);
    }

}