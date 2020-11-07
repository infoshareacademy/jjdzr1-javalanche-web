package com.infoshareacademy.restapi;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static com.infoshareacademy.App.STDOUT;

public class Request {

    public static final String API_URL = "http://localhost:8081/api/dayoff";
    private String updatedResponse;

    public String sendGet() throws IOException {
        URL urlForGetRequest = new URL(API_URL);
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode1 = connection.getResponseCode();

        if (responseCode1 == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
                updatedResponse = response.toString();
                return updatedResponse;
            }
            in.close();

        } else {
            STDOUT.warn("GET NOT WORKED");
        }
        connection.disconnect();
        System.out.println(updatedResponse);
        return updatedResponse;
    }

    public List<Request> jsonToList() {
        String json = updatedResponse;
        Type listType = new TypeToken<ArrayList<Request>>() {
        }.getType();

        return new Gson().<ArrayList<Request>>fromJson(json, listType);
    }
}

