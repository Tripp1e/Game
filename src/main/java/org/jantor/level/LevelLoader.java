package org.jantor.level;

import org.jantor.level.element.Element;
import org.jantor.level.element.block.Dirt;
import org.jantor.level.element.block.Grass;
import org.jantor.level.element.block.Sand;
import org.jantor.level.element.block.Stone;
import org.jantor.level.element.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LevelLoader {
    private static final Map<String, Class<? extends Element>> blockRegistry = new HashMap<>();

    static {
        blockRegistry.put("stone", Stone.class);
        blockRegistry.put("grass", Grass.class);
        blockRegistry.put("dirt", Dirt.class);
        blockRegistry.put("player", Player.class);
        blockRegistry.put("sand", Sand.class);
    }



    private static Element createBlock(String blockType) {
        try {
            Class<? extends Element> elementClass = blockRegistry.get(blockType);
            if (elementClass != null) {
                return elementClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}