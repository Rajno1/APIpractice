package org.requestBuilders;

import io.restassured.response.Response;
import org.requestBuilders.BaseRequestBuilder;


public class PostRequestCall extends BaseRequestBuilder <PostRequestCall> {

    @Override
    public Response send() {
//        Response response = prepareRequest().when().post(endpoint).then().log().all().extract().response();
//        storeResponse("POST", response);
//        return response;
        logRequestToReport("POST"); // Log request parameters to Extent report

        Response response = prepareRequest()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();

        storeResponse("POST", response);
        return response;
    }

    @Override
    protected PostRequestCall self() {
        return this;
    }
}
