package org.jantor.level;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Collectable;
import org.jantor.elements.Player;
import org.jantor.screens.Screen;
import org.jantor.shop.Shop;
import org.jantor.utils.Renderer;
import org.jantor.utils.Vector2D;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jantor.elements.Block.BlockType;
import org.jantor.elements.Collectable.CollectableType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.jantor.utils.JsonReader.getJsonObject;

public class Level extends Screen {
    Renderer renderer;

    public ArrayList<Object[]> blockInfo = new ArrayList<>();
    public Vector2D playerCoords;
    public ArrayList<Object[]> collectableInfo = new ArrayList<>();

    private JSONObject levelJson;

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
        InputStream inputStream = getClass().getResourceAsStream(filename);
        if (inputStream == null) { System.err.println("Error: Could not find the file: " + filename); return; }
        try {
            levelJson = getJsonObject(inputStream);
        } catch (IOException ignored) {}

        JSONArray playerJson = levelJson.getJSONArray("player");
        playerCoords = new Vector2D(playerJson.getInt(0), playerJson.getInt(1));
        renderer.player = new Player(playerCoords);

        processLevelItems("collectables", collectableInfo, CollectableType::getByString);
        loadCollectables();
        System.out.println(collectableInfo.toString());

        processLevelItems("blocks", blockInfo, BlockType::getByString);
        loadBlocks();

        renderer.width = levelJson.getInt("width");
    }

    interface TypeResolver {
        Object getType(String itemType);
    }
    private void processLevelItems(String key, List<Object[]> infoList, TypeResolver typeResolver) {
        JSONObject itemsJson = levelJson.getJSONObject(key);
        for (String itemType : itemsJson.keySet()) {
            JSONArray coordsArray = itemsJson.getJSONArray(itemType);
            Object type = typeResolver.getType(itemType.toUpperCase());
            System.out.println("Type: " + type);

            for (int i = 0; i < coordsArray.length(); i++) {
                JSONArray coords = coordsArray.getJSONArray(i);
                int x = coords.getInt(0);
                int y = coords.getInt(1);

                Vector2D vector = new Vector2D(x, y);
                System.out.println("Position: " + vector);
                if (type == null) continue;

                infoList.add(new Object[]{type, vector});
            }
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
        for (Object[] info : collectableInfo) {
            Collectable.CollectableType type = (Collectable.CollectableType) info[0];
            Vector2D position = (Vector2D) info[1];

            Collectable collectable = new Collectable(type, position);

            renderer.collectables.add(collectable);
        }
    }

    @Override
    public void act() {
        super.act();
        if (Greenfoot.isKeyDown("S") && getObjects(Shop.class).isEmpty()) {
            addObject(new Shop(), 0, 0);
            System.out.println("Shop opened");
        }
    }

}
