package org.jantor.level;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class LevelSerializer {
    public static JSONObject toJSON(String[][] levelData) {
        int height = levelData.length;
        int width = levelData[0].length;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("width", width);
        jsonObject.put("height", height);

        jsonObject.put("blocks", levelData);

        return jsonObject;
    }

    public static void saveToFile(String[][] levelData, String filename) {
        JSONObject levelJson = toJSON(levelData);

        StringBuilder formattedBlocks = getStringBuilder(levelData);

        String jsonOutput = String.format("{\n  \"width\": %d,\n  \"height\": %d,\n%s\n}",
                levelJson.getInt("width"),
                levelJson.getInt("height"),
                formattedBlocks);

        try (FileWriter fileWriter = new FileWriter("resources/levels/" + filename)) {
            fileWriter.write(jsonOutput);
            System.out.println("Level saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving level to file: " + filename);
        }
    }

    private static StringBuilder getStringBuilder(String[][] levelData) {
        StringBuilder formattedBlocks = new StringBuilder();
        formattedBlocks.append("  \"blocks\": [\n");

        for (int i = 0; i < levelData.length; i++) {
            formattedBlocks.append("    [");
            for (int j = 0; j < levelData[i].length; j++) {
                formattedBlocks.append(String.format("\"%-8s\"", levelData[i][j]));
                if (j < levelData[i].length - 1) {
                    formattedBlocks.append(", ");
                }
            }
            formattedBlocks.append("]");
            if (i < levelData.length - 1) {
                formattedBlocks.append(",\n");
            } else {
                formattedBlocks.append("\n");
            }
        }
        formattedBlocks.append("  ]");
        return formattedBlocks;
    }
}
