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

    public static String createBug(String summary, String description) {
        String body = JsonUtils.readJsonAndReplacePlaceholders(
                "jsondata/bugtemplate.json",
                summary,
                description,
                PropertyReader.getConfig().jiraProjectKey(),
                "High",
                "your.username"
        );

        Response response = RestAssured.given()
                .baseUri(PropertyReader.getConfig().jiraBaseUrl())
                .header("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .post("/rest/api/3/issue");

        if (response.statusCode() == 201) {
            String issueKey = response.jsonPath().getString("key");
            System.out.println("✅ Created JIRA bug: " + issueKey);
            return issueKey;
        } else {
            System.err.println("❌ Bug creation failed: " + response.statusCode());
            System.err.println(response.asString());
            return null;
        }
    }



    public static void attachFile(String issueKey, String filePath) {
        Response attachResponse = RestAssured.given()
                .baseUri(PropertyReader.getConfig().jiraBaseUrl())
                .header("Authorization", getAuthHeader())
                .header("X-Atlassian-Token", "no-check") // IMPORTANT
                .multiPart("file", new java.io.File(filePath))
                .log().all()
                .post("/rest/api/3/issue/" + issueKey + "/attachments");

        if (attachResponse.getStatusCode() == 200) {
            System.out.println("✅ Attached file " + filePath + " to issue " + issueKey);
        } else {
            System.err.println("❌ Failed to attach file: " + attachResponse.getStatusCode());
            System.err.println(attachResponse.asString());
        }
    }

}
