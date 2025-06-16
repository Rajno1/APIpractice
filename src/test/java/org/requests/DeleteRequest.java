package org.requests;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @Test
    public void deleteUser(){
       Response response =  given()
                .pathParam("id",4)
                .log()
                .all()
                .delete("http://localhost:3000/employees/{id}");

       response.prettyPrint();
        System.out.println(" Received Status Code :" + response.statusCode());
    }
}
