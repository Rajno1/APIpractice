package org.requestBuilders;

import io.restassured.response.Response;

public class PutCallRequest extends BaseRequestBuilder<PutCallRequest> {
    public PutCallRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    public Response send() {
        return prepareRequest()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
