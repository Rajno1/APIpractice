package org.httpRequests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.config.PropertyReader;
import org.testng.annotations.Test;
import org.utils.TestDataFactory;

public class PutRequest {

    @Test
    public void updateData(){

           Response response = given()
                    .baseUri(PropertyReader.getConfig().baseUri())
                    .header("Content-Type", ContentType.JSON)
                    .pathParams("id",2)
                    .log()
                    .all()
                    .body(TestDataFactory.updateIdTwo())
                    .put(PropertyReader.getConfig().employeeEndPoint()+ "/{id}");

            response.prettyPrint();
        System.out.println(" Received Status Code :" + response.statusCode());
    }

}
