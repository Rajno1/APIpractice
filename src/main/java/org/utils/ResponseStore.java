package org.utils;
import io.restassured.response.Response;

public class ResponseStore {
    private static final ThreadLocal<Response> responseThread = new ThreadLocal<>();

    public static void setResponse(Response response) {
        responseThread.set(response);
    }

    public static Response getResponse() {
        return responseThread.get();
    }

    public static void clear() {
        responseThread.remove();
    }
}
