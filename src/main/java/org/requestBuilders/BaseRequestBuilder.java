package org.requestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.config.PropertyReader;
import org.utils.ResponseWriter;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequestBuilder<T extends BaseRequestBuilder<T>> {

    protected String endpoint;
    protected Map<String, Object> pathParams = new HashMap<>();
    protected Map<String, Object> queryParams = new HashMap<>();
    protected String body;

    public T setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return self();
    }

    public T addPathParam(String key, Object value) {
        this.pathParams.put(key, value);
        return self();
    }

    public T addQueryParam(String key, Object value) {
        this.queryParams.put(key, value);
        return self();
    }

    public T setBody(String body) {
        this.body = body;
        return self();
    }

    // NEW METHOD: Accepts POJO and converts to JSON
    public T setBody(Object pojo) {
        try {
            this.body = new ObjectMapper().writeValueAsString(pojo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert POJO to JSON", e);
        }
        return self();
    }

    protected RequestSpecification prepareRequest() {
        RequestSpecification request = RestAssured.given()
                .baseUri(PropertyReader.getConfig().baseUri())
                .log().all();

        if (!pathParams.isEmpty()) request.pathParams(pathParams);
        if (!queryParams.isEmpty()) request.queryParams(queryParams);
        if (body != null) request.body(body);

        return request;
    }

    public abstract Response send();

    protected void storeResponse(String method, Response response) {
        String endpointName = endpoint.replaceAll("[/{}]", "_");
        ResponseWriter.writeResponse(method, endpointName, response.asPrettyString());
    }

    protected abstract T self();
}
