package com.infoshareacademy.service;

import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import com.infoshareacademy.model.NationalHoliday;
import com.infoshareacademy.repository.NationalHolidayRepository;

import javax.ejb.Local;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Local
public class NationalHolidayService {

    @Inject
    private NationalHolidayRepository nationalHolidayRepository;

    private static final Logger LOGGER = Logger.getLogger(CalendarService.class.getName());

    public void executeApiTransferRequest(String requestedYear, String apiKey) {
        //FIXME!!!
/*        try{*/
            if(!findIfHolidaysAlreadyInDatabase(Integer.parseInt(requestedYear))){
                String apiURL = generateApiFromUrl(requestedYear, apiKey);
                List<Holidays> jsonHolidays = HolidaysJsonData.readNationalHolidaysFromApiUrl(apiURL);
                if (!jsonHolidays.isEmpty()){
                    transferNationalHolidaysFromJsonToDatabase(jsonHolidays);
             }
            }/*
            return true;
        } catch (Exception e) {
            LOGGER.warning(() -> e.getMessage());
            return false;
        }*/

    }

    private void transferNationalHolidaysFromJsonToDatabase(List<Holidays> jsonList) {
        jsonList.forEach(holiday ->
                nationalHolidayRepository.create(
                        new NationalHoliday(
                        holiday.getName(),
                        holiday.getDescription(),
                        holiday.getHolidayDateInLocalDateFormat()
                        )));
    }

    private boolean findIfHolidaysAlreadyInDatabase(int year){
        return nationalHolidayRepository.getAll().stream().anyMatch(holiday -> holiday.getHolidayDate().getYear()==year);
    }

    private String generateApiFromUrl(String requestedYear, String apiKey) {
        return "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=PL&year=" + requestedYear + "&type=national";
    }

}

