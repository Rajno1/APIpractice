package org.testutils;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.BaseTest;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.*;

import static org.testng.Assert.*;


import static io.restassured.RestAssured.*;


public class AssertUtils extends BaseTest {

    public static void assertStandardResponses(Response response) {

//        logger.info("-- Standard Response Assertion -- ");
//        logger.info("Received Status Code : " + response.getStatusCode());
//        assertEquals(response.getStatusCode(), 200,"Status code mismatch");
//        logger.info("Received Status Line : " + response.getStatusLine());
//        assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Unexpected status line");
//
//        long responseTime = response.getTime();
//        logger.info("Received Response Time : " + responseTime);
//        assertTrue(responseTime<1200,"Response time is greater than 1200ms");
//        assertEquals(response.getContentType(),"application/json","Unexpected Content Type");

        logger.info("-- Standard GET Response Assertion -- ");

        assertThat(response.getStatusCode()).as("Status code mismatch").isEqualTo(200);
        logger.info("Received Status Code : " + response.getStatusCode());


        assertThat(response.getStatusLine()).as("Unexpected status line").isEqualTo("HTTP/1.1 200 OK");
        logger.info("Received Status Line : " + response.getStatusLine());

        long responseTime = response.getTime();
        assertThat(responseTime).as("Response time is greater than 1200ms").isLessThan(1200);
        logger.info("Received Response Time : " + responseTime);

        assertThat(response.getContentType()).as("Unexpected Content Type").isEqualTo("application/json");
        logger.info("Received Content-Type : " + response.getContentType());
    }


    public static void assertJsonString(Response response, String path, String expectedValue) {
        String actualValue = response.jsonPath().getString(path);
        assertThat(actualValue).as("Mismatch in JSON value for path").isEqualTo(expectedValue);
        //assertEquals(actualValue, expectedValue, "Mismatch in JSON value for path: " + path);

    }





    public static void getResponseHeaders(Headers headers){
        logger.info(" -- Below is the Response Header -- ");

        for (Header header: headers){
            logger.info(header.getName() +" : "+ header.getValue());
        }
    }


    public static void assertStandardPostResponse(Response response){

        logger.info("-- Standard POST Response Assertion -- ");

        assertThat(response.getStatusCode()).as("Status code mismatch").isEqualTo(201);
        logger.info("Received Status Code : " + response.getStatusCode());


        assertThat(response.getStatusLine()).as("Unexpected status line").isEqualTo("HTTP/1.1 201 Created");
        logger.info("Received Status Line : " + response.getStatusLine());

        long responseTime = response.getTime();
        assertThat(responseTime).as("Response time is greater than 1200ms").isLessThan(1200);
        logger.info("Received Response Time : " + responseTime);

        assertThat(response.getContentType()).as("Unexpected Content Type").isEqualTo("application/json");
        logger.info("Received Content-Type : " + response.getContentType());
    }
}
