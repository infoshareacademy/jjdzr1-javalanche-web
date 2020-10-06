package com.infoshareacademy.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URL;
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

    public static HolidaysJsonData readDataFromJsonFile(String fileName) {
        //if(fileName == null) {fileName = "API.json";}
        fileName = "APIs/" + fileName;
        Gson gson = new Gson();
        HolidaysJsonData holidaysJSONData = new HolidaysJsonData();
        JsonReader jsonReader = null;
        try {
            File file = new File(HolidaysJsonData.class.getClassLoader().getResource(fileName).getFile());
            jsonReader = new JsonReader(new FileReader(file));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.fromJson(jsonReader, HolidaysJsonData.class);
    }

    //FIXME replace code responsible for holidays in calendar
    public static List<Holidays> returnOnlyHolidaysAsList(){
        return new ArrayList<>(HolidaysJsonData.readDataFromJsonFile("API.json").getServerResponse().getHolidays());
    }

    public static List<Holidays> findHolidaysByYear(String year){
        String [] foundAPIs;
        File apisFolder = new File("/home/kacper-kwiatkowski/Programing/Java/ISA/Project/jjdzr1-javalanche-web/src/main/resources/APIs");
        foundAPIs = apisFolder.list();
        for(String file : foundAPIs){
            if(file.contains(year)){
                return readDataFromJsonFile(file).getServerResponse().getHolidays();
            }
        }
        return null;
    }

}

