package com.infoshareacademy.service;

import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Local
public class CalendarService {

    private List<String> calendarView;
    private int sizeOfCalendar;
    private Map<LocalDate, String> holidaysMap;

    public CalendarService() {
        this.sizeOfCalendar = 30;
        setHolidaysMap();
        setCalendarView();
    }

    private Map<LocalDate, String> getHolidaysMap() {
        return holidaysMap;
    }

    private void setHolidaysMap() {
        Map<LocalDate, String> holidaysMap = new LinkedHashMap<>();
        for (Holidays holiday : HolidaysJsonData.returnOnlyHolidaysAsList()) {
            holidaysMap.put(holiday.getHolidayDateInLocalDateFormat(), holiday.getName());
        }
        this.holidaysMap = holidaysMap;
    }

    public List<String> getCalendarView() {
        return calendarView;
    }

    public void setCalendarView() {
        List<String> calendarView = new ArrayList<>();
        for (int i = 0; i < getSizeOfCalendar(); i++) {
            if (getHolidaysMap().containsKey(LocalDate.now().plusDays(i))) {
                calendarView.add(holidaysMap.get(LocalDate.now().plusDays(i))+"<br>"+LocalDate.now().plusDays(i));
            }
            else {
                calendarView.add(LocalDate.now().plusDays(i).getDayOfWeek()+"<br>"+LocalDate.now().plusDays(i));
            }

            this.calendarView = calendarView;
        }
    }

    public int getSizeOfCalendar() {
        return sizeOfCalendar;
    }

    public void setSizeOfCalendar(int sizeOfCalendar) {
        this.sizeOfCalendar = sizeOfCalendar;
    }
}
