package org.jantor.level;

import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Collectable;
import org.jantor.elements.Player;
import org.jantor.screens.Screen;
import org.jantor.utils.Renderer;
import org.jantor.utils.Vector2D;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jantor.elements.Block.BlockType;

import java.io.InputStream;
import java.util.ArrayList;

import static org.jantor.utils.JsonReader.getJsonObject;

public class Level extends Screen {
    Renderer renderer;

    public ArrayList<Object[]> blockInfo = new ArrayList<>();
    public Vector2D playerCoords;
    public ArrayList<Vector2D> collectableCoords = new ArrayList<>();

    public Level(String filename) {
        super();

        renderer = new Renderer(this);
        setPaintOrder(Player.class);

        loadLevel(filename);

        Constants.renderer = renderer;
        Constants.world = this;

        renderer.init();
    }

    private void loadLevel(String filename) {
        filename = "/levels/" + filename + ".json";
        try {
            InputStream inputStream = getClass().getResourceAsStream(filename);
            if (inputStream == null) { System.err.println("Error: Could not find the file: " + filename); return; }

            JSONObject levelJson = getJsonObject(inputStream);

            JSONArray playerJson = levelJson.getJSONArray("player");
            playerCoords = new Vector2D(playerJson.getInt(0), playerJson.getInt(1));

            renderer.player = new Player(playerCoords);

            JSONArray collectablesJson = levelJson.getJSONArray("collectables");
            for (int i = 0; i < collectablesJson.length(); i++) {
                JSONArray coordsJson = collectablesJson.getJSONArray(i);
                Vector2D coords = new Vector2D(coordsJson.getInt(0), coordsJson.getInt(1));
                collectableCoords.add(coords);
            }
            loadCollectables();

            JSONObject blocksJson = levelJson.getJSONObject("blocks");

            for (String blockType : blocksJson.keySet()) {
                JSONArray coordsArray = blocksJson.getJSONArray(blockType);

                for (int i = 0; i < coordsArray.length(); i++) {
                    JSONArray coords = coordsArray.getJSONArray(i);
                    int x = coords.getInt(0);
                    int y = coords.getInt(1);

                    Vector2D vector = new Vector2D(x, y);
                    BlockType type = BlockType.getByString(blockType.toUpperCase());
                    if (type == null) continue;

                    blockInfo.add(new Object[]{type, vector});
                }
            }
            loadBlocks();

            renderer.width = levelJson.getInt("width");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBlocks() {
        for (Object[] info : blockInfo) {
            Block.BlockType type = (Block.BlockType) info[0];
            Vector2D position = (Vector2D) info[1];

            Block block = new Block(type, position);

            renderer.blocks.add(block);
        }
    }

    private void loadCollectables() {
        for (Vector2D coords: collectableCoords) {
            Collectable collectable = new Collectable(Collectable.CollectableType.COIN, coords);
            renderer.collectables.add(collectable);
        }
    }

}
