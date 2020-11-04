package com.infoshareacademy.restapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.xdevapi.JsonArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    public static final String API_URL = "http://localhost:8081/api/dayoff";
    String updatedResponse = "";
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
            System.out.println("GET NOT WORKED");
        }
        connection.disconnect();
        System.out.println(updatedResponse);
        return updatedResponse;
    }
    public Map jsonToList (){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(updatedResponse);


      }
    }
}
