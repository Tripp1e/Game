package org.jantor.levels;

import org.jantor.entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class LevelLoader {
    public static Block[][] loadLevel(String filename) {
        Block[][] blocks = null;

        try (FileReader file = new FileReader(filename)) {
            // Parse the JSON file
            JSONObject levelData = new JSONObject(new JSONTokener(file));

            int width = levelData.getInt("width");
            int height = levelData.getInt("height");

            blocks = new Block[height][width];  // Correct dimensions: height x width

            JSONArray blockArray = levelData.getJSONArray("blocks");

            // Loop through the JSON data and create Block objects
            for (int y = 0; y < height; y++) {
                JSONArray row = blockArray.getJSONArray(y);
                for (int x = 0; x < width; x++) {
                    String blockTypeString = row.getString(x);
                    switch (blockTypeString) {
                        case "stone":
                            blocks[y][x] = new Stone();
                            break;
                        case "grass":
                            blocks[y][x] = new Grass();
                            break;
                        case "dirt":
                            blocks[y][x] = new Dirt();
                            break;
                        case "player":
                            blocks[y][x] = new Player();
                            break;
                        default:
                            blocks[y][x] = null;
                            break;
                    }
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return blocks;
    }
}
