package org.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.config.PropertyReader;

import java.util.Base64;



public class JiraUtils {

    private JiraUtils() {
        // prevent instantiation
    }

    public static String getAuthHeader() {
        String auth = PropertyReader.getConfig().jiraEmail() + ":" + PropertyReader.getConfig().jiraApiToken();
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public static void createBug(String summary, String description) {
        String filePath = "src/test/resources/jsondata/bugtemplate.json";
        String body = JsonUtils.readJsonAndReplacePlaceholders(
                filePath,
                summary,
                description,
                PropertyReader.getConfig().jiraProjectKey()
        );

        Response response = RestAssured.given()
                .baseUri(PropertyReader.getConfig().jiraBaseUrl())
                .header("Authorization", getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(PropertyReader.getConfig().jiraCreateIssueEndpoint());

        if (response.statusCode() == 201) {
            System.out.println("✅ Bug created: " + response.jsonPath().getString("key"));
        } else {
            System.err.println("❌ Bug creation failed: " + response.statusCode());
            System.err.println(response.asString());
        }
    }


}
