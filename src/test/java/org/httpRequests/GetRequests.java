package org.httpRequests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.config.PropertyReader;
import org.testng.annotations.Test;
import org.testutils.AssertUtils;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


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


}
