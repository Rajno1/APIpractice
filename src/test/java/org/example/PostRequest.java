package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;

import static io.restassured.RestAssured.*;
import static java.nio.file.Files.readAllBytes;


public class PostRequest {

    private static final Logger log = LoggerFactory.getLogger(PostRequest.class);

    @Test
    public void firstApproach() {
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
        System.out.println("Response Recieved " + response.getStatusLine());
    }


    //Second Method -> Passing Request body from external json file

    /**
     * int this approach the issue is
     * as we are passing the JSON file path as body
     * in out put the JSON file path will display as request body to over come this we use third approach
     * @throws IOException
     */
    @Test
    public void secondApproach() throws IOException {

        Response response = given()
                   .header("Content-Type",ContentType.JSON)
                   .log()
                   .all()
                   .body(new File(System.getProperty("user.dir")+"/data.json"))
                   .post("http://localhost:3000/employees");
        response.prettyPrint();
        System.out.println("Response Recieved " + response.getStatusLine());
    }

    /**
     * in this Third approach we are fetching JSON request body from the file and
     * converting into String using the 'readAllBytes()' method from 'Files' class from
     * java.nio.Files package
     */

    @Test
    public void thirdApproach() throws IOException {

        String jsonBody = new String(Files.readAllBytes(Paths.get("data.json")));

      Response response =  given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(jsonBody)
                .post("http://localhost:3000/employees");

      response.prettyPrint();
        System.out.println(" Received Status Code :" + response.statusCode());

    }
}

