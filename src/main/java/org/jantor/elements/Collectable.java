package org.jantor.elements;

import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

public class Collectable extends Element {
    public final CollectableType type;

    public enum CollectableType {
        COIN,
        STAR;

        private String filePath() {
            return "images/collectables/" + name().toLowerCase() + ".png";
        }

        public GreenfootImage getImage() {
            return new GreenfootImage(filePath());
        }
        public String toString() { return this.name().toLowerCase(); }

        public static CollectableType getByString(String name) {
            try {
                return CollectableType.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    public Collectable(CollectableType type, Vector2D position) {
        super(type.getImage(), position);
        this.type = type;
    }

}