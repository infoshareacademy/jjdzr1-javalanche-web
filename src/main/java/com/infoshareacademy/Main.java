package com.infoshareacademy;

import com.infoshareacademy.service.CalendarService;

public class Main {

    public static void main(String[] args) {

        CalendarService calendarService = new CalendarService();

        System.out.println(calendarService.getCalendarView());

        for (String s: calendarService.getCalendarView()) {
            System.out.println(s.contains("SUNDAY"));
        }
    }
}
