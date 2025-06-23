package org.utils;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {

    private JsonUtils() {
        // prevent instantiation
    }


    public static String readJsonAsString(String resourcePath) {
        try (InputStream is = JsonUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("File not found in resources: " + resourcePath);
            }
            return new String(is.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file from resources: " + resourcePath, e);
        }
    }

    public static String readJsonAndReplacePlaceholders(String resourcePath, String summary,
                                                        String description, String projectKey,
                                                        String priority, String assignee) {
        String json = readJsonAsString(resourcePath);
        return json.replace("@SUMMARY@", escapeJson(summary))
                .replace("@DESCRIPTION@", escapeJson(description))
                .replace("@PROJECTKEY@", escapeJson(projectKey))
                .replace("@PRIORITY@", priority)
                .replace("@ASSIGNEE@", assignee);
    }

    private static String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
