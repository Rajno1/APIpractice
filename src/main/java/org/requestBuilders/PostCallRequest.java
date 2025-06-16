package org.requestBuilders;

import io.restassured.response.Response;

public class PostCallRequest extends BaseRequestBuilder<PostCallRequest> {
    public PostCallRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    public Response send() {
        return prepareRequest()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
