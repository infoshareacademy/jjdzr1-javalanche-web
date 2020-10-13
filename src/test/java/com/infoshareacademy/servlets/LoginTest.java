package com.infoshareacademy.servlets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginTest {
    public static final String BASE_URL = "http://localhost:8080/";

    @Test
    void getReturn200() throws IOException, InterruptedException {
        HttpClient httpClient= HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        Assertions.assertEquals(200,actualCode);
    }
}
