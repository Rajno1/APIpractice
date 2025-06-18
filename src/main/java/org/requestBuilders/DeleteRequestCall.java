package org.requestBuilders;

import io.restassured.response.Response;

public class DeleteRequestCall extends BaseRequestBuilder<DeleteRequestCall> {
    @Override
    public Response send() {
        logRequestToReport("DELETE"); // Log request parameters to Extent report
        Response response = prepareRequest()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();

        storeResponse("DELETE", response);
        return response;
    }

    @Override
    protected DeleteRequestCall self() {
        return this;
    }
}
