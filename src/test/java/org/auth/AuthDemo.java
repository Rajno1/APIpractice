package org.auth;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class AuthDemo {

    @Test
    public void basicAuthtest(){


        // using basic Authentication

        Response response = given().
                auth()
                .basic("postman","password")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();

        /**
         * As we are passing Authentication directly we need to hide this header
         */
        Response response1 = given()
                .header("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA==")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response1.prettyPrint();




    }
}
