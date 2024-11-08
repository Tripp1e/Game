package org.jantor.levels;

import org.jantor.entities.Grass;
import org.jantor.entities.Stone;
import org.jantor.screens.Screen;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

import org.jantor.entities.*;

public class Level extends Screen {
    private int width;
    private int height;
    private String[][] blocks;

    public Level(String filename) {
        super();  // Set world size
        setPaintOrder(Player.class);
        loadLevel("/levels/" + filename);
        createBlocks();

    }
    private void loadLevel(String filename) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filename);

            if (inputStream == null) {
                System.err.println("Error: Could not find the file: " + filename);
                return;
            }

            JSONObject levelJson = getJsonObject(inputStream);
            width = levelJson.getInt("width");
            height = levelJson.getInt("height");

            System.out.println("Level Dimensions: " + width + "x" + height);

            blocks = new String[height][width];

            JSONArray blocksJson = levelJson.getJSONArray("blocks");
            System.out.println("Blocks JSON Length: " + blocksJson.length());

            for (int y = 0; y < height; y++) {
                if (y >= blocksJson.length()) {
                    System.err.println("Error: Row " + y + " is missing in the blocks array.");
                    break;
                }

                JSONArray row = blocksJson.getJSONArray(y);

                if (row.length() != width) {
                    System.err.println("Error: Row " + y + " does not have the expected number of columns (expected: " + width + ", found: " + row.length() + ")");
                }

                for (int x = 0; x < width; x++) {
                    blocks[y][x] = row.getString(x);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getJsonObject(InputStream inputStream) throws IOException {
        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder jsonContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }

        return new JSONObject(jsonContent.toString());
    }

    public void createBlocks() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String blockTypeString = blocks[y][x];

                switch (blockTypeString) {
                    case "stone":
                        Stone stone = new Stone();
                        stone.addTo(this, x, y);
                        break;
                    case "grass":
                        Grass grass = new Grass();
                        grass.addTo(this, x, y);
                        break;
                    case "dirt":
                        Dirt dirt = new Dirt();
                        dirt.addTo(this, x, y);
                        break;
                    case "player":
                        Player player = new Player();
                        player.addTo(this, x, y);
                        break;
                    default:
                        Air air = new Air();
                        air.addTo(this, x, y);
                        break;
                }
            }
        }
    }


}
