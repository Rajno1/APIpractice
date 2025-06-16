package org.requestBuilders;

import io.restassured.response.Response;

public class PatchCallRequest extends BaseRequestBuilder<PatchCallRequest> {
    public PatchCallRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    public Response send() {
        return prepareRequest()
                .when()
                .patch(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
