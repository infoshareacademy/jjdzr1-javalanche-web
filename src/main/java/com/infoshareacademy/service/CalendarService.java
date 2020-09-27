package com.infoshareacademy.service;

import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import javax.ejb.LocalBean;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@LocalBean
public class CalendarService {

    private static final Logger LOGGER = Logger.getLogger(CalendarService.class.getName());
    private static final String BR = "<br>";

    private Map<LocalDate, String> holidaysMap() {
        Map<LocalDate, String> holidaysMap = new LinkedHashMap<>();
        for (Holidays holiday : HolidaysJsonData.returnOnlyHolidaysAsList()) {
            holidaysMap.put(holiday.getHolidayDateInLocalDateFormat(), holiday.getName());
        }
        return holidaysMap;
    }

    public List<String> calendarView(int sizeOfCalendar) {
        List<String> calendarView = new ArrayList<>();
        for (int i = 0; i < sizeOfCalendar; i++) {
            if (holidaysMap().containsKey(LocalDate.now().plusDays(i))) {
                calendarView.add(holidaysMap().get(LocalDate.now().plusDays(i))+ BR +LocalDate.now().plusDays(i));
            }
            else {
                calendarView.add(LocalDate.now().plusDays(i).getDayOfWeek()+ BR +LocalDate.now().plusDays(i));
            }
        }
        return calendarView;
    }


}
