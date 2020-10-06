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

    public void executeApiTransferRequest(String requestedYear) {
        String apiURL = generateApiFromUrl(requestedYear);
        transferNationalHolidaysFromJsonToDatabase(apiURL);
    }

    public void transferNationalHolidaysFromJsonToDatabase(String apiUrl) {
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

    private String generateApiFromUrl(String requestedYear) {
        return "https://calendarific.com/api/v2/holidays?api_key=bad0ddcffc6542810f3cfb5749d38c63b5b7929a&country=PL&year=" + requestedYear + "&type=national";
    }

}

