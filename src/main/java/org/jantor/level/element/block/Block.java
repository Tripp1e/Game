package org.jantor.level.element.block;

import org.jantor.image.BlockImage;
import org.jantor.level.element.Element;

public abstract class Block extends Element {
    public static int width = 50;
    public static int height = 50;

    public Block(BlockImage image) {
        super(image);
    }

}
