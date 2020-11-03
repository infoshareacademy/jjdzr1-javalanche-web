package com.infoshareacademy;

import com.infoshareacademy.restapi.ParameterStringBuilder;
import com.infoshareacademy.service.DayOffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * jAvalanche
 */
public class App {

    public static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static final String API_URL = "http://localhost:8081/api/dayoff";
    public static void main(String[] args) throws IOException {


        final String POST_PARAMS = "{\n" + "\"id\": 1,\r\n" +

        "\"userId\": 1\r\n}";

        System.out.println(POST_PARAMS);

        URL obj = new URL(API_URL);

        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

        postConnection.setRequestMethod("POST");

        postConnection.setRequestProperty("userId","1");

        postConnection.setRequestProperty("Content-Type", "application/json");

        postConnection.setDoOutput(true);

        OutputStream os = postConnection.getOutputStream();

        os.write(POST_PARAMS.getBytes());

        os.flush();

        os.close();


        int responseCode = postConnection.getResponseCode();

        System.out.println("POST Response Code :  " + responseCode);

        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success

            BufferedReader in = new BufferedReader(new InputStreamReader(

                    postConnection.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {

                response.append(inputLine);

            } in .close();

            // print result

            System.out.println(response.toString());

        } else {

            System.out.println("POST NOT WORKED");

        }

        URL urlForGetRequest = new URL(API_URL);

        String readLine = null;

        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();

        conection.setRequestMethod("GET");

        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here

        int responseCode1 = conection.getResponseCode();

        if (responseCode1 == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(

                    new InputStreamReader(conection.getInputStream()));

            StringBuffer response = new StringBuffer();

            while ((readLine = in .readLine()) != null) {

                response.append(readLine);

            } in .close();

            // print result

            System.out.println("JSON String Result " + response.toString());

            //GetAndPost.POSTRequest(response.toString());

        } else {

            System.out.println("GET NOT WORKED");

        }




}
//        STDOUT.info("jAvalanche \n");
//
//
//
//            URL url = new URL(API_URL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("id", "userId");
//
//            con.setDoOutput(true);
//            DataOutputStream out = new DataOutputStream(con.getOutputStream());
//            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
//            out.flush();
//            out.close();
//
//            con.setRequestProperty("Content-Type", "application/json");
//            String contentType = con.getHeaderField("Content-Type");
//            con.setConnectTimeout(5000);
//            con.setReadTimeout(5000);
//
//            int status = con.getResponseCode();
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//
//            Reader streamReader = null;
//
//            if (status > 299) {
//                streamReader = new InputStreamReader(con.getErrorStream());
//            } else {
//                streamReader = new InputStreamReader(con.getInputStream());
//            }
//            System.out.println(content.toString());
//        }
    }

