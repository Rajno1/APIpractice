package org.requests;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.BaseTest;
import org.config.PropertyReader;
import org.reports.ExtentLogger;
import org.requestBuilders.GetRequestCall;
import org.testng.annotations.Test;
import org.testutils.AssertUtils;
import org.reports.ExtentManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.testutils.AssertUtils.getResponseHeaders;


public class GetRequests extends BaseTest {

    // Before starting should have idea about Static Import
    // Restassured we can write both in BDD and Non BDD way
    
    @Test
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

    @Test
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
