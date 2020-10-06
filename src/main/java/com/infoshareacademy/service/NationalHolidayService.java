package com.infoshareacademy.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import com.infoshareacademy.model.NationalHoliday;
import com.infoshareacademy.repository.NationalHolidayRepository;

import javax.ejb.Local;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Local
public class NationalHolidayService {

    @Inject
    private NationalHolidayRepository nationalHolidayRepository;

    public void executeApiTransferRequest(String requestedYear) {
        String apiURL = generateApiUrl(requestedYear);
        String fileSaveLocation = generateFileSaveLocation(requestedYear);
        String requestedJson = getUrlContents(apiURL);
        retrieveApiFromUrlAndSaveToPropertiesFolder(requestedJson, fileSaveLocation);
        transferNationalHolidaysFromJsonToDatabase(requestedYear);
    }

    public void transferNationalHolidaysFromJsonToDatabase(String requestedYear) {
        List<Holidays> jsonHolidays = HolidaysJsonData.findHolidaysByYear(requestedYear);
        NationalHoliday nationalHoliday;
        for (Holidays holiday : jsonHolidays) {
            if (nationalHolidayRepository.findByDate(holiday.getHolidayDateInLocalDateFormat()) == null) {
                nationalHoliday = new NationalHoliday();
                nationalHoliday.setName(holiday.getName());
                nationalHoliday.setDescription(holiday.getDescription());
                nationalHoliday.setHolidayDate(holiday.getHolidayDateInLocalDateFormat());
                nationalHolidayRepository.create(nationalHoliday);
            }
        }
    }

    public void retrieveApiFromUrlAndSaveToPropertiesFolder(String requestedJson, String fileSaveLocation) {
        JsonObject jsonObject = new JsonParser().parse(requestedJson).getAsJsonObject();

        try (FileWriter file = new FileWriter(fileSaveLocation)) {
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return getUrlContents("https://calendarific.com/api/v2/holidays?api_key=bad0ddcffc6542810f3cfb5749d38c63b5b7929a&country=PL&year=2021&type=national");
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private String generateApiUrl(String requestedYear) {
        return "https://calendarific.com/api/v2/holidays?api_key=bad0ddcffc6542810f3cfb5749d38c63b5b7929a&country=PL&year=" + requestedYear + "&type=national";
    }

    private String generateFileSaveLocation(String requestedYear) {
        //FIXME
        return "/home/kacper-kwiatkowski/Programing/Java/ISA/Project/jjdzr1-javalanche-web/src/main/resources/APIs/nationalHolidays_" + requestedYear + ".json";
    }

}

