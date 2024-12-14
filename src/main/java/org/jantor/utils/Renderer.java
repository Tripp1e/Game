package org.jantor.utils;

import org.jantor.constants.Constants;
import org.jantor.elements.Block;
import org.jantor.elements.Collectable;
import org.jantor.elements.Player;
import org.jantor.level.Level;

import java.util.ArrayList;

public class Renderer {
    private final Level level;
    public Player player = null;
    public final ArrayList<Block> blocks = new ArrayList<>();
    private final ArrayList<Block> floorBlocks = new ArrayList<>();
    public final ArrayList<Collectable> collectables = new ArrayList<>();

    public int width = 20;

    public Renderer(Level level) {
        this.level = level;
    }

    public void init() {
        loadFloor();
        loadBlocks();
        loadCollectables();
        renderBackground();
        loadPlayer();
    }

    public void updateBlocks() {
        for (Block block : blocks) {
            block.updateLocation();
        }
        for (Block block : floorBlocks) {
            block.updateLocation();
        }
        for (Collectable collectable : collectables) {
            collectable.updateLocation();
        }
    }

    private void loadFloor() {
        for (int i = -width / 2; i < width; i++) {
            floorBlocks.add(new Block(Block.BlockType.GRASS, new Vector2D(i, 2)));
            floorBlocks.add(new Block(Block.BlockType.DIRT,  new Vector2D(i, 1)));
            floorBlocks.add(new Block(Block.BlockType.DIRT,  new Vector2D(i, 0)));
        }
    }

    private void loadCollectables() {
        for (Collectable coll: collectables) {
            coll.addTo(level);
        }
    }

    private void loadBlocks() {
        for (Block block: blocks) {
            if(block.isWithinScreenBounds()) block.addTo(level);
        }
        for (Block block: floorBlocks) {
            block.addTo(level);
        }
    }

    private void loadPlayer() {
        player.addTo(level);
    }

    private void renderBackground() {
        GreenfootImage background = new GreenfootImage("images/levels/background.png");
        level.setBackground(background);
    }


}