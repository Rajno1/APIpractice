package org.requestBuilders;

import io.restassured.response.Response;

public class PutRequestCall extends BaseRequestBuilder<PutRequestCall> {
    @Override
    public Response send() {

        logRequestToReport("PUT"); // Log request parameters to Extent report
        Response response = prepareRequest()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();

        storeResponse("PUT", response);
        return response;
    }

    @Override
    protected PutRequestCall self() {
        return this;
    }
}
