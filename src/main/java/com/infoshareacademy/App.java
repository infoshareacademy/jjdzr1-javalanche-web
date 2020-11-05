package com.infoshareacademy;

import com.infoshareacademy.restapi.ParameterStringBuilder;
import com.infoshareacademy.restapi.Request;
import com.infoshareacademy.service.DayOffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.infoshareacademy.restapi.Request.API_URL;

/**
 * jAvalanche
 */
public class App {

    public static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static final String API_URL = "http://localhost:8081/api/dayoff";

    public static void main(String[] args) throws IOException {
        STDOUT.info("jAvalanche");
    }
}