package org.jantor.level.element.block;

import org.jantor.image.BlockImage;

public class Grass extends Block {
    public static BlockImage image = new BlockImage("grass.png");

    public Grass() {
        super(image);
    }
}
