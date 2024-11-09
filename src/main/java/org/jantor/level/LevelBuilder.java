package org.jantor.level;

import java.util.Arrays;

public class LevelBuilder {
    private final String[][] blocks;

    public LevelBuilder(int width, int height) {
        blocks = new String[height][width];
        for (String[] row : blocks) {
            Arrays.fill(row, "air");  // Default all blocks to "air"
        }
    }

    public LevelBuilder setBlock(int x, int y, String blockType) {
        blocks[y][x] = blockType;
        return this;
    }
    public LevelBuilder setRow( int row, String blockType) {
        Arrays.fill(blocks[row], "air");
        return this;
    }

    public String[][] build() {
        return blocks;
    }

    public static LevelBuilder createLevel(int width, int height) {
        return new LevelBuilder(width, height);
    }
}
