package org.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    private JsonUtils() {
        // prevent instantiation
    }

    public static String readJsonAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    /**
     * Reads the JSON file and replaces placeholders with actual values.
     *
     * //@param filePath Path to the JSON file
     * @param summary Summary text for the bug
     * @param description Description text for the bug
     * @param projectKey Project key for the bug
     * @return JSON string with replaced values
     */
//    public static String readJsonAndReplacePlaceholders(String filePath, String summary, String description, String projectKey) {
//        String json = readJsonAsString(filePath);
//        return json.replace("@SUMMARY@", escapeJson(summary))
//                .replace("@DESCRIPTION@", escapeJson(description))
//                .replace("@PROJECTKEY@", escapeJson(projectKey));
//    }
//
//    /**
//     * Escapes double quotes in input to make it safe for JSON.
//     */
//    private static String escapeJson(String input) {
//        return input.replace("\"", "\\\"");
//    }

    public static String readJsonAndReplacePlaceholders(String resourcePath, String summary, String description, String projectKey) {
        String json = readJsonAsString(resourcePath);
        return json.replace("@SUMMARY@", escapeJson(summary))
                .replace("@DESCRIPTION@", escapeJson(description))
                .replace("@PROJECTKEY@", escapeJson(projectKey));
    }

    private static String escapeJson(String input) {
        if (input == null) {
            return ""; // safety
        }
        return input
                .replace("\\", "\\\\")    // escape backslashes
                .replace("\"", "\\\"")    // escape quotes
                .replace("\n", "\\n")     // escape new lines
                .replace("\r", "\\r");    // escape returns
    }

}
