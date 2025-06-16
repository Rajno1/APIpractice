package org.requestBuilders;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.config.PropertyReader;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequestBuilder<T extends BaseRequestBuilder<T>> {
    protected String endpoint;
    protected Object body;
    protected final Map<String, Object> pathParams = new HashMap<>();
    protected final Map<String, Object> queryParams = new HashMap<>();

    public BaseRequestBuilder(String endpoint) {
        this.endpoint = endpoint;
    }

    public T addPathParam(String key, Object value) {
        this.pathParams.put(key, value);
        return (T) this;
    }

    public T addQueryParam(String key, Object value) {
        this.queryParams.put(key, value);
        return (T) this;
    }

    public T setRequestBody(Object body) {
        this.body = body;
        return (T) this;
    }

    protected RequestSpecification prepareRequest() {
        RequestSpecification request = RestAssured
                .given()
                .baseUri(PropertyReader.getConfig().baseUri())
                .log().all();

        if (!pathParams.isEmpty()) {
            request.pathParams(pathParams);
        }

        if (!queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        if (body != null) {
            request.contentType("application/json").body(body);
        }

        return request;
    }

    public abstract Response send();
}
