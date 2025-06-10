package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class PostRequest {

    private static final Logger log = LoggerFactory.getLogger(PostRequest.class);

    @Test
    public void postTest() {
        /**
         * Along with the POST request we need to send the Request Body, and we have different ways t
         * send POST request.
         */

        //Sending JSON body as a string

       Response response = given()
                .header("Content-Type",ContentType.JSON)
                .body("{\n" +
                        "        \"id\": \"4\",\n" +
                        "        \"FirstName\": \"Santosh\",\n" +
                        "        \"LastName\": \"Raju\",\n" +
                        "        \"Role\": \"QA Analyst\"\n" +
                        "    }")
                .log()
                .all()
                .post("http://localhost:3000/employees");
        response.prettyPrint();


    }

}
