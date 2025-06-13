package org.httpRequests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.config.PropertyReader;
import org.testng.annotations.Test;
import org.testutils.AssertUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testutils.AssertUtils.getResponseHeaders;


public class GetRequests {

    // Before starting should have idea about Static Import
    // Restassured we can write both in BDD and Non BDD way
    
    @Test
    public void getAllEmp(){

        Response response = given()
                .baseUri(PropertyReader.getConfig().baseUri())
                .log()
                .all()
                .get(PropertyReader.getConfig().employeeEndPoint());

        response.prettyPrint();

        AssertUtils.assertStandardResponses(response);

        // get the list of Response Headers
        Headers headers = response.headers();
        for (Header header :headers){
            System.out.println(header.getName() + ":" + header.getValue());
        }

    }

    @Test
    public void getEmpTwo(){ // Get Employee with id 2 using path parameters
        Response response =  given()
                .pathParam("id",2)
                .baseUri(PropertyReader.getConfig().baseUri())
                .log()
                .all()
                .get(PropertyReader.getConfig().employeeEndPoint()+"/{id}");

        AssertUtils.assertStandardResponses(response);

        response.prettyPrint();
    }

    @Test
    public void getUserTwo(){  // Get Employee with id 2 using Query parameters
       Response response = given()
                .baseUri(PropertyReader.getConfig().baseUri())
                .queryParam("id",2)
                .log()
                .all()
                .get(PropertyReader.getConfig().employeeEndPoint());

       AssertUtils.assertStandardResponses(response);
    }


    @Test
    public void getUserSix() throws IOException {
        Response response = given()
                .baseUri(PropertyReader.getConfig().baseUri())
                .pathParam("id",6)
                .log()
                .all()
                .get(PropertyReader.getConfig().employeeEndPoint()+"/{id}");

        response.prettyPrint();

        getResponseHeaders(response.headers());

        AssertUtils.assertStandardResponses(response);

        // Asserting JSON value
        AssertUtils.assertJsonString(response,"address[1].district","RangaReddy");

        // JSON Schema Validation
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

        // String response in an external file
        Files.write(Paths.get(System.getProperty("user.dir")+"/response.json"),response.asByteArray());

    }
}
