package org.example;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static java.lang.System.getProperty;

public class GetRequests {

    // Before starting should have idea about Static Import
    // Restassured we can write both in BDD and Non BDD way
    
    @Test
    public void getTest(){

        Response response = given().get("http://localhost:3000/employees");

        // get the list of Response Headers

        Headers headers = response.headers();

        for (Header header :headers){
            System.out.println(header.getName() + ":" + header.getValue());
        }

        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getTime() = " + response.getTime());
    }

}
