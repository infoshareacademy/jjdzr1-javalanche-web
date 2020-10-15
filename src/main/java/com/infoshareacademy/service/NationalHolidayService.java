package com.infoshareacademy.service;

import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import com.infoshareacademy.model.NationalHoliday;
import com.infoshareacademy.repository.NationalHolidayRepository;

import javax.ejb.Local;
import javax.inject.Inject;
import java.util.List;

@Local
public class NationalHolidayService {

    @Inject
    private NationalHolidayRepository nationalHolidayRepository;

    public void executeApiTransferRequest(String requestedYear, String apiKey) {
        try{
            String apiURL = generateApiFromUrl(requestedYear, apiKey);
            transferNationalHolidaysFromJsonToDatabase(apiURL);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void transferNationalHolidaysFromJsonToDatabase(String apiUrl) {
        List<Holidays> jsonHolidays;
        jsonHolidays = HolidaysJsonData.readNationalHolidaysFromApiUrl(apiUrl);
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

    private String generateApiFromUrl(String requestedYear, String apiKey) {
        return "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=PL&year=" + requestedYear + "&type=national";
    }

}

