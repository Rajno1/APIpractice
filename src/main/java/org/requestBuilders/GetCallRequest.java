package org.requestBuilders;

import io.restassured.response.Response;

public class GetCallRequest extends BaseRequestBuilder<GetCallRequest> {
    public GetCallRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    public Response send() {
        return prepareRequest()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
