package com.infoshareacademy;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.List;

/**
 * jAvalanche
 */
public class App {

    public static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void main(String[] args) {
/*
        try {
            URL url = new URL("https://calendarific.com/api/v2/holidays?api_key=bad0ddcffc6542810f3cfb5749d38c63b5b7929a&country=PL&year=2021&type=national");
            BufferedReader bufferedReader = new BufferedReader()
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/



/*
        List<HolidaysJsonData> test = gson.fromJson(jsonReader, HolidaysJsonData.class);
        test.forEach(System.out::println);
        */
    }
}
