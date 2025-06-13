package org.testutils;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.BaseTest;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.*;

public class AssertUtils extends BaseTest {

    public static void assertStandardResponses(Response response) {

        logger.info("Received Status Code : " + response.getStatusCode());
        assertEquals(response.getStatusCode(), 200,"Status code mismatch");

        logger.info("Received Status Line : " + response.getStatusLine());
        assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Unexpected status line");

        long responseTime = response.getTime();
        logger.info("Received Response Time : " + responseTime);
        assertTrue(responseTime<1200,"Response time is greater than 1200ms");
        assertEquals(response.getContentType(),"application/json","Unexpected Content Type");
    }


    public static void assertJsonString(Response response, String path, String expectedValue) {
        String actualValue = response.jsonPath().getString(path);
        assertEquals(actualValue, expectedValue, "Mismatch in JSON value for path: " + path);
    }







    public static void getResponseHeaders(Headers headers){
        logger.info(" -- Below is the Response Header -- ");

        for (Header header: headers){
            logger.info(header.getName() +" : "+ header.getValue());
        }
    }
}
