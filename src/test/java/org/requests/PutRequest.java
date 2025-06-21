package org.requests;

import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.annotations.FrameworkAnnotation;
import org.config.PropertyReader;
import org.reportBuilder.ExtentLogger;
import org.requestBuilders.PutRequestCall;
import org.testng.annotations.Test;
import org.utils.TestDataFactory;

@FrameworkAnnotation(author = {"QA Team"}, category = {"Regression"})
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

        ExtentLogger.logResponse(putResponse.asPrettyString());

    }

}
