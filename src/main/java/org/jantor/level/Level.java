package org.jantor.level;

import org.jantor.elements.Block;
import org.jantor.utils.GreenfootImage;
import org.jantor.elements.Element;
import org.jantor.elements.Player;
import org.jantor.screens.Screen;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jantor.elements.Block.BlockType;

import java.io.InputStream;
import java.util.Arrays;

import static org.jantor.utils.JsonReader.getJsonObject;

public class Level extends Screen {
    private int width;
    private int height;
    private String[][] elements;
    private int[] playerCoords;
    public Player player;

    public Level(String filename) {
        super();
        setPaintOrder(Player.class);
        loadLevel(filename);
        configureBlocks();
        configurePlayer();
        configureBackground();
    }

    private void loadLevel(String filename) {
        filename = "/levels/" + filename + ".json";
        try {
            InputStream inputStream = getClass().getResourceAsStream(filename);

            if (inputStream == null) {
                System.err.println("Error: Could not find the file: " + filename);
                return;
            }

            JSONObject levelJson = getJsonObject(inputStream);
            width = levelJson.getInt("width");
            height = levelJson.getInt("height");

            playerCoords = new int[2];
            JSONArray playerCoordsJson = levelJson.getJSONArray("player");
            for (int i = 0; i < playerCoordsJson.length(); i++) {
                playerCoords[i] = playerCoordsJson.getInt(i);
            }

            elements = new String[height][width];

            JSONArray blocksJson = levelJson.getJSONArray("blocks");

            for (int y = 0; y < height; y++) {
                if (y >= blocksJson.length()) break;

                JSONArray row = blocksJson.getJSONArray(y);

                for (int x = 0; x < width; x++) {
                    elements[y][x] = row.getString(x).replace(" ", "").toUpperCase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configureBlocks() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String blockTypeString = elements[y][x];

                BlockType blockType = BlockType.getByString(blockTypeString);

                if (blockType == null) continue;
                Element element = new Block(blockType);

                element.addTo(this, x, y);
            }
        }
    }

    private void configurePlayer() {
        player = new Player();
        player.addTo(this, playerCoords[0], playerCoords[1]);
    }

    private void configureBackground() {
        GreenfootImage background = new GreenfootImage("images/levels/background.png");
        setBackground(background);
    }
}
