package org.requestBuilders;

import io.restassured.response.Response;

public class DeleteCallRequest extends BaseRequestBuilder<DeleteCallRequest> {
    public DeleteCallRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    public Response send() {
        return prepareRequest()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
