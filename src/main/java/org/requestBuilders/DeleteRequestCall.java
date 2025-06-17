package org.requestBuilders;

import io.restassured.response.Response;

public class DeleteRequestCall extends BaseRequestBuilder<DeleteRequestCall> {
    @Override
    public Response send() {
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
