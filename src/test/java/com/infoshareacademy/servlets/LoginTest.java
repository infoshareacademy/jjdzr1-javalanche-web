package com.infoshareacademy.servlets;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static UI.BaseClass.TEST_LOGIN;
import static UI.BaseClass.TEST_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    public static final String BASE_URL = "http://localhost:8080/";
    HttpClient httpClient = HttpClient.newBuilder().build();
    HttpResponse<Void> response;

    @Test
    void getReturn200() throws IOException, InterruptedException {
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .GET()
                .build();

        response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        assertEquals(200, actualCode);
    }

    @Test
    void postReturn200() throws IOException, InterruptedException {
        HttpRequest post = HttpRequest.newBuilder(URI.create(BASE_URL + "login"))
                .POST(HttpRequest.BodyPublishers.ofString("username: " + TEST_LOGIN +
                        "password: " + TEST_PASSWORD))
                .build();

        response = httpClient.send(post, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        assertEquals(200, actualCode);
    }

}
