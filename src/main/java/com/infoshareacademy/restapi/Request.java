package com.infoshareacademy.restapi;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Request {

    public static final String API_URL = "http://localhost:8081/api/dayoff";
    private Integer id;
    private Integer userId;
    private String updatedResponse = "";
    private List<Request> requestList = new ArrayList<>();


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

    public Map<String, String> jsonToList() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
      //  JsonObject object = new JsonParser().parse(updatedResponse).getAsJsonObject();
      //  Request request[] = gson.fromJson(updatedResponse,Request[].class);
                //(JsonObject) parser.parse(updatedResponse);
        //Request jsonObject = gson.fromJson(object, Request.class);
//        JsonObject jsonObject = object.getAsJsonObject("object");
       // Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        //requestList.add(jsonObject);
        //return requestList;
       // return entrySet;
        Type mapType = new TypeToken<List<Map<String, String>>>(){}.getType();
        //List<Map<Integer,Integer>> list = gson.fromJson(updatedResponse, typeOfList);
        Map<String,String> stringMap = new Gson().fromJson(updatedResponse, mapType);

        return stringMap;
    }
}

