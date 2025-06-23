package org.requests;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.BaseTest;
import org.annotations.FrameworkAnnotation;
import org.config.PropertyReader;
import org.reportBuilder.ExtentLogger;
import org.requestBuilders.GetRequestCall;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testutils.AssertUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.testutils.AssertUtils.getResponseHeaders;

@Listeners(org.listener.TestListener.class)
@FrameworkAnnotation(author = {"QA Team"}, category = {"Regression"})
public class GetRequests extends BaseTest {

    // Before starting should have idea about Static Import
    // Restassured we can write both in BDD and Non BDD way
    @FrameworkAnnotation(author = {"Raj"}, category = {"Smoke"})
    @Test(description = "Get list of all employees and validate standard assertions")
    public void getAllEmp(){

        String employeeEndPoint = PropertyReader.getConfig().employeeEndPoint();
            Response allEmpResponse= new GetRequestCall()
                    .setEndpoint(employeeEndPoint)
                    .send();

        ExtentLogger.logResponse(allEmpResponse.asPrettyString());

            AssertUtils.assertStandardResponses(allEmpResponse);
            AssertUtils.getResponseHeaders(allEmpResponse.getHeaders());
    }
    @FrameworkAnnotation(author = {"Raj"}, category = {"Smoke"})
    @Test(description = "Get Employee Details of Id 2")
    public void getEmpTwoUsingPathParams(){
        String endpoint = PropertyReader.getConfig().employeeEndPoint();
        Response getEmpTwo = new GetRequestCall()
                .setEndpoint(endpoint+"/{id}")
                .addPathParam("id",2)
                .send();

        ExtentLogger.logResponse(getEmpTwo.asPrettyString());
        AssertUtils.getResponseHeaders(getEmpTwo.getHeaders());
        AssertUtils.assertStandardResponses(getEmpTwo);
    }
    @SneakyThrows
    @FrameworkAnnotation(author = {"Raj"}, category = {"Smoke"})
    @Test
    public void getEmpTwoUsngQueryParms(){  // Get Employee with id 2 using Query parameters
        String endpoint = PropertyReader.getConfig().employeeEndPoint();
        Response response =  new GetRequestCall()
                    .setEndpoint(endpoint)
                    .addQueryParam("id",2)
                    .send();

       AssertUtils.assertStandardResponses(response);

        // Asserting JSON value
        AssertUtils.assertJsonString(response,"[0].address[1].district","RangaReddy");

        // JSON Schema Validation
//        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

        // String response in an external file
      //  Files.write(Paths.get(System.getProperty("user.dir")+"/response.json"),response.asByteArray());

    }

}
