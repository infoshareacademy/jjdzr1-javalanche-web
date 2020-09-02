package com.infoshareacademy.api;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HolidaysJsonData {
    @SerializedName("meta")
    @Expose
    private ServerInfo serverInfo;
    @SerializedName("response")
    @Expose
    private ServerResponse serverResponse;

    public HolidaysJsonData() {
    }

    public HolidaysJsonData(ServerInfo serverInfo, ServerResponse serverResponse) {
        this.serverInfo = serverInfo;
        this.serverResponse = serverResponse;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    @Override
    public String toString() {
        return "Server info: " + serverInfo.toString() + "\n";
    }

    public static HolidaysJsonData readDataFromJsonFile() {
        Gson gson = new Gson();
        HolidaysJsonData holidaysJSONData = new HolidaysJsonData();
        JsonReader jsonReader = null;
        try {
            File file = new File(HolidaysJsonData.class.getClassLoader().getResource("db_holidaysNational.json").getFile());
            jsonReader = new JsonReader(new FileReader(file));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.fromJson(jsonReader, HolidaysJsonData.class);
    }

    public static List<Holidays> returnOnlyHolidaysAsList(){
        return new ArrayList<>(HolidaysJsonData.readDataFromJsonFile().getServerResponse().getHolidays());
    }

}

