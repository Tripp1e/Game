package org.jantor.level;

import org.jantor.level.element.block.Block;
import org.jantor.level.element.block.Stone;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class LevelSaver {
    public static void saveLevel(Stone[][] blocks, String filename) {
        JSONObject levelData = new JSONObject();
        levelData.put("width", blocks.length);
        levelData.put("height", blocks[0].length);

        JSONArray blockArray = getObjects(blocks);

        levelData.put("blocks", blockArray);

        try (FileWriter file = new FileWriter(filename)) {
            file.write(levelData.toString(4)); // Write with indentation for readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray getObjects(Stone[][] blocks) {
        JSONArray blockArray = new JSONArray();

        // Loop through the block grid and add block types to the JSON array
        for (int y = 0; y < blocks[0].length; y++) {
            JSONArray row = new JSONArray();
            for (Block[] value : blocks) {
                Block block = value[y];
                String blockType = block != null ? block.getClass().getName().toLowerCase() : "empty"; // Assume Block has a getType() method
                row.put(blockType);
            }
            blockArray.put(row);
        }
        return blockArray;
    }
}
