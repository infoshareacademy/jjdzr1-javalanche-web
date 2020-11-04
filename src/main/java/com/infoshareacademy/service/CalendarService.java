package com.infoshareacademy.service;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.NationalHoliday;
import com.infoshareacademy.repository.NationalHolidayRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@LocalBean
public class CalendarService {

    private static final Logger LOGGER = Logger.getLogger(CalendarService.class.getName());

    @Inject
    private NationalHolidayRepository nationalHolidayRepository;

    @Inject
    private UserRepository userRepository;

    private Map<LocalDate, String> holidaysMap() {
        Map<LocalDate, String> holidaysMap = new LinkedHashMap<>();
        for (NationalHoliday holiday : nationalHolidayRepository.getAll()) {
            holidaysMap.put(holiday.getHolidayDate(), holiday.getName());
        }
        return holidaysMap;
    }

    public List<String> calendarView(int sizeOfCalendar) {
        List<String> calendarView = new ArrayList<>();
        for (int i = 0; i < sizeOfCalendar; i++) {
            if (holidaysMap().containsKey(LocalDate.now().plusDays(i))) {
                calendarView.add(holidaysMap().get(LocalDate.now().plusDays(i)) + "<br>" + LocalDate.now().plusDays(i));
            } else {
                calendarView.add(LocalDate.now().plusDays(i).getDayOfWeek() + "<br>" + LocalDate.now().plusDays(i));
            }
        }
        return calendarView;
    }

    public List<String> workingDaysList() {
        return calendarView(366)
                .stream()
                .filter(hol -> hol.startsWith("MONDAY")
                        || hol.startsWith("TUESDAY")
                        || hol.startsWith("WEDNESDAY")
                        || hol.startsWith("THURSDAY")
                        || hol.startsWith("FRIDAY"))
                .map(hol -> hol.substring(hol.length() - 10))
                .collect(Collectors.toList());
    }

    public List<String> accountableWorkingDaysList(String loggedUser) {
        List<String> accountableWorkingDays = workingDaysList();
        accountableWorkingDays.removeAll(loggedUsersPendingHolidaysAsStrings(loggedUser));
        return accountableWorkingDays;
    }

    private List<String> loggedUsersPendingHolidaysAsStrings(String username) {
        return usersPendingDaysOffAsStrings(
                userRepository
                        .findByEmail(username)
                        .getDaysOff()
                        .stream()
                        .filter(dayOff -> !dayOff.isAccepted())
                        .collect(Collectors.toSet())
        );

    }

    public List<String> usersPendingDaysOffAsStrings(Set<DayOff> loggerUserPendingHolidays) {
        List<String> formattedPendingHolidays = new ArrayList<>();
        for (DayOff dayOff : loggerUserPendingHolidays) {
            dayOff.getListOfDays().forEach(hol -> formattedPendingHolidays.add(hol.toString()));
        }
        return formattedPendingHolidays;
    }
}
