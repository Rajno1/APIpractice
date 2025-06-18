package org.requests;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.BaseTest;
import org.annotations.FrameworkAnnotation;
import org.config.PropertyReader;
import org.reports.ExtentLogger;
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
         //   allEmpResponse.prettyPrint();

       // ExtentManager.getExTest().pass(allEmpResponse.prettyPrint());
       // ExtentManager.getExTest().pass(MarkupHelper.createCodeBlock(allEmpResponse.prettyPrint(), CodeLanguage.JSON));

        ExtentLogger.logResponse(allEmpResponse.asPrettyString());

            AssertUtils.assertStandardResponses(allEmpResponse);
            AssertUtils.getResponseHeaders(allEmpResponse.getHeaders());
    }
    @FrameworkAnnotation(author = {"Raj"}, category = {"Smoke"})
    @Test(description = "Get Employee Details of Id 2")
    public void getEmpTwo(){
        String endpoint = PropertyReader.getConfig().employeeEndPoint();
        Response getEmpTwo = new GetRequestCall()
                .setEndpoint(endpoint+"/{id}")
                .addPathParam("id",2)
                .send();

       // getEmpTwo.prettyPrint();
       // ExtentManager.getExTest().pass(MarkupHelper.createCodeBlock(getEmpTwo.prettyPrint(), CodeLanguage.JSON));

        ExtentLogger.logResponse(getEmpTwo.asPrettyString());
        AssertUtils.getResponseHeaders(getEmpTwo.getHeaders());
        AssertUtils.assertStandardResponses(getEmpTwo);
    }
    @FrameworkAnnotation(author = {"Raj"}, category = {"Smoke"})
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

    @FrameworkAnnotation(author = {"Raj"}, category = {"Smoke"})
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
