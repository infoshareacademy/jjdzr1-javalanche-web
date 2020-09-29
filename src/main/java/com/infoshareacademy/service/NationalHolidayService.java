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

    public void transferNationalHolidaysFromJsonToDatabase(){
        List<Holidays>jsonHolidays = HolidaysJsonData.returnOnlyHolidaysAsList();
        NationalHoliday nationalHoliday;
        for(Holidays holiday : jsonHolidays){
            if(nationalHolidayRepository.findByDate(holiday.getHolidayDateInLocalDateFormat())==null){
                nationalHoliday = new NationalHoliday();
                nationalHoliday.setName(holiday.getName());
                nationalHoliday.setDescription(holiday.getDescription());
                nationalHoliday.setHolidayDate(holiday.getHolidayDateInLocalDateFormat());
                nationalHolidayRepository.create(nationalHoliday);
            }
        }
    }
}
