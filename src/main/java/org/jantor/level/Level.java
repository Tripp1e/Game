package org.jantor.level;

import org.jantor.level.element.Element;
import org.jantor.level.element.block.Block;
import org.jantor.level.element.block.Dirt;
import org.jantor.level.element.block.Grass;
import org.jantor.level.element.block.Stone;
import org.jantor.level.element.entity.Player;
import org.jantor.screen.Screen;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Level extends Screen {
    public Player player;
    private int width;
    private int height;
    private String[][] elements;
    private static final Map<String, Class<? extends Element>> BLOCK_MAP = new HashMap<>();

    static {
        BLOCK_MAP.put("stone", Stone.class);
        BLOCK_MAP.put("grass", Grass.class);
        BLOCK_MAP.put("dirt", Dirt.class);
        BLOCK_MAP.put("player", Player.class);
    }

    public Level(String filename) {
        super();
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

            elements = new String[height][width];

            JSONArray blocksJson = levelJson.getJSONArray("blocks");

            for (int y = 0; y < height; y++) {
                if (y >= blocksJson.length()) break;

                JSONArray row = blocksJson.getJSONArray(y);

                for (int x = 0; x < width; x++) {
                    elements[y][x] = row.getString(x).replace(" ", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getJsonObject(InputStream inputStream) throws IOException {
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
                String blockTypeString = elements[y][x];
                Class<? extends Element> blockClass = BLOCK_MAP.get(blockTypeString);

                if (blockClass != null) {
                    try {
                        Element element = blockClass.getDeclaredConstructor().newInstance();
                        if (element instanceof Player) {
                            this.player = (Player) element;
                        }
                        element.addTo(this, x, y);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
