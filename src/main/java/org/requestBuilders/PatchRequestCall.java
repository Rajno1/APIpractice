package org.requestBuilders;

import io.restassured.response.Response;

public class PatchRequestCall extends BaseRequestBuilder<PatchRequestCall> {
    @Override
    public Response send() {
        Response response = prepareRequest()
                .when()
                .patch(endpoint)
                .then()
                .log().all()
                .extract()
                .response();

        storeResponse("PATCH", response);
        return response;
    }

    @Override
    protected PatchRequestCall self() {
        return this;
    }
}
