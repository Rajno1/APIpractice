package org.requests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.config.PropertyReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.requestBuilders.PostRequestCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testutils.AssertUtils;
import org.utils.JsonUtils;
import org.utils.TestDataFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.File;

import static io.restassured.RestAssured.*;


public class PostRequest {

    private static final Logger log = LoggerFactory.getLogger(PostRequest.class);

    @Test
    public void firstApproach() {
        /**
         * Along with the POST request we need to send the Request Body, and we have different ways t
         * send POST request.
         */

        //Sending JSON body as a string

       Response response = given()
                .header("Content-Type",ContentType.JSON)
                .body("{\n" +
                        "        \"id\": \"4\",\n" +
                        "        \"FirstName\": \"Santosh\",\n" +
                        "        \"LastName\": \"Raju\",\n" +
                        "        \"Role\": \"QA Analyst\"\n" +
                        "    }")
                .log()
                .all()
                .post("http://localhost:3000/employees");

       response.prettyPrint();
        System.out.println("Response Recieved " + response.getStatusLine());
    }


    //Second Method -> Passing Request body from external json file

    /**
     * int this approach the issue is
     * as we are passing the JSON file path as body
     * in out put the JSON file path will display as request body to over come this we use third approach
     * @throws IOException
     */
    @Test
    public void secondApproach() throws IOException {

        Response response = given()
                   .header("Content-Type",ContentType.JSON)
                   .log()
                   .all()
                   .body(new File(System.getProperty("user.dir")+"/data.json"))
                   .post("http://localhost:3000/employees");
        response.prettyPrint();
        System.out.println("Response Recieved " + response.getStatusLine());
    }

    /**
     * in this Third approach we are fetching JSON request body from the file and
     * converting into String using the 'readAllBytes()' method from 'Files' class from
     * java.nio.Files package
     */

    @Test
    public void thirdApproach() throws IOException {

        String jsonBody = new String(Files.readAllBytes(Paths.get("data.json")));

      Response response =  given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(jsonBody)
                .post("http://localhost:3000/employees");

      response.prettyPrint();
        System.out.println(" Received Status Code :" + response.statusCode());

    }

    @Test
    public void fouthApproach(){
        // Sending POST request using JSON library
        //{} --> use JsonObejct from org.json
        //[] --> use JsonArray

        JSONObject obj = new JSONObject();
        Faker faker = new Faker();
        obj.put("id", faker.number().numberBetween(4,10));
        obj.put("FirstName", faker.name().firstName());
        obj.put("LastName",faker.name().lastName());
        obj.put("Role", "Developer");
        obj.put("Email", faker.internet().emailAddress());


        JSONArray jobsList = new JSONArray();
        jobsList.put("Analust");
        jobsList.put("Designer");

        obj.put("Jobs",jobsList);


        JSONObject foodlist = new JSONObject();
        foodlist.put("breakfast","idle");
        foodlist.put("lunch","Biryani");

        JSONArray dinnertime = new JSONArray();
        dinnertime.put("Chapathi");
        dinnertime.put("upma");

        foodlist.put("dinner",dinnertime);
        obj.put("FavFoods",foodlist);


        Response response =  given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(obj)
                .post("http://localhost:3000/employees");

        response.prettyPrint();
        System.out.println(" Received Status Code :" + response.statusCode());

    }

    @Test
    public void fourthApprochModified(){
        /**
         * in this modified fourth approach i am using POJO {plain old java object}
         * {} --> Create a POJO class
         * [] --> list type
         *
         *  Make sure you have created the POJO classes
         * Added Lombok dependency to use Getter and settet in POJO classes
         */
        Response response =  given()
                .baseUri(PropertyReader.getConfig().baseUri())
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(TestDataFactory.employeeData())
                .post(PropertyReader.getConfig().employeeEndPoint());

        response.prettyPrint();
        System.out.println(" Received Status Code :" + response.statusCode());
    }

    @Test
    public void postAfterBuilder(){

        String endpoint = PropertyReader.getConfig().employeeEndPoint();


        // Approach 2: Using JSON File
        String jsonFromFile = JsonUtils.readJsonAsString("data.json");

        Response postResponse = new PostRequestCall()
                .setEndpoint(endpoint)
                .setBody(jsonFromFile)
                .send();

       postResponse.prettyPrint();
        AssertUtils.assertStandardPostResponse(postResponse);
        AssertUtils.getResponseHeaders(postResponse.getHeaders());
    }
}

