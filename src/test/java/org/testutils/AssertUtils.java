package org.testutils;

import io.restassured.response.Response;
import org.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.*;



public class AssertUtils extends BaseTest {

    public static void assertStandardResponses(Response response) {

        logger.info("Received Status Code : " + response.getStatusCode());
        assertEquals(response.getStatusCode(), 200,"Status code mismatch");

        logger.info("Received Status Line : " + response.getStatusLine());
        assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Unexpected status line");

        long responseTime = response.getTime();
        logger.info("Received Response Time : " + responseTime);
        assertTrue(responseTime<600,"Response time is greater than 600ms");
        assertEquals(response.getContentType(),"application/json","Unexpected Content Type");
    }
}
