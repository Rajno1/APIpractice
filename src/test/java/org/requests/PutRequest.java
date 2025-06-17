package org.requests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.config.PropertyReader;
import org.pojo.Employee;
import org.requestBuilders.PutRequestCall;
import org.testng.annotations.Test;
import org.testutils.AssertUtils;
import org.utils.TestDataFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PutRequest {

    @SneakyThrows
    @Test
    public void updateData(){

//           Response response = given()
//                    .baseUri(PropertyReader.getConfig().baseUri())
//                    .header("Content-Type", ContentType.JSON)
//                    .pathParams("id",2)
//                    .log()
//                    .all()
//                    .body(TestDataFactory.updateIdTwo())
//                    .put(PropertyReader.getConfig().employeeEndPoint()+ "/{id}");
//
//            response.prettyPrint();
//        System.out.println(" Received Status Code :" + response.statusCode());



        String endpoint = PropertyReader.getConfig().employeeEndPoint();

       Response putResponse =  new PutRequestCall()
                .setEndpoint(endpoint)
                .setBody(TestDataFactory.employeeData())
                .send();

        putResponse.prettyPrint();

    }

}
