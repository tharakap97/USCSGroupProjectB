package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class JsonReader {
    private static final Logger logger = Logger.getLogger(JsonReader.class.getName());
    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File("src/test/resources/testdata/joinData.json"));
        } catch (IOException e) {
            logger.severe("Error reading JSON file: " + e.getMessage());
        }
    }

    public static String get(String userType, String field) {
        return rootNode.get(userType).get(field).asText();
    }

    public static java.util.List<String> getList(String userType, String field) {
        java.util.List<String> result = new java.util.ArrayList<>();
        JsonNode arrayNode = rootNode.get(userType).get(field);
        if (arrayNode.isArray()) {
            for (JsonNode node : arrayNode) {
                result.add(node.asText());
            }
        }
        return result;
    }

    public static Map<String, List<String>> getMap(String userType, String field) {
        Map<String, List<String>> resultMap = new HashMap<>();
        JsonNode parentNode = rootNode.get(userType).get(field);

        if (parentNode != null && parentNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = parentNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                List<String> subCategories = new ArrayList<>();

                if (entry.getValue().isArray()) {
                    for (JsonNode node : entry.getValue()) {
                        subCategories.add(node.asText());
                    }
                }
                resultMap.put(entry.getKey(), subCategories);
            }
        }
        return resultMap;
    }
}