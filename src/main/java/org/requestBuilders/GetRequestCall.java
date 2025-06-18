package org.requestBuilders;

import io.restassured.response.Response;

public class GetRequestCall extends BaseRequestBuilder<GetRequestCall> {
    @Override
    public Response send() {
        logRequestToReport("GET"); // Log request parameters to Extent report
        Response response = prepareRequest().
                when()
                .get(endpoint).
                then().
                log().
                all().extract().response();
        storeResponse("GET", response);
        return response;
    }

    @Override
    protected GetRequestCall self() {
        return this;
    }
}
