package org.jantor.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonReader {

    public static JSONObject getJsonObject(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder jsonContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }

        return new JSONObject(jsonContent.toString());
    }

}
