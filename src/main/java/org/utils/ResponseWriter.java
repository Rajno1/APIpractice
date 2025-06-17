package org.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ResponseWriter {

    public static void writeResponse(String method, String endpointName, String responseBody) {
        // Format timestamp
        String timestamp = LocalDateTime.now().toString().replace(":", "-");

        // Build directory and file path
        String folderPath = "src/test/resources/responses/" + method.toLowerCase();
        String fileName = method.toLowerCase() + "_" + endpointName + "_" + timestamp + ".json";

        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        File file = new File(folder, fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(responseBody);
            System.out.println("Response stored at: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
