package com.infoshareacademy.utils;

import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class CalendarUtil {

    @Inject
    UserRepository userRepository;

    private User user;
    private Integer daysOffCounter;
    private List<LocalDate> dayOff;

    public static void main(String[] args) {

    }

    public LocalDate calculateMaximumPossibleDayForHoliday (String firstDate, String loggedUser){

        initiateObjects(firstDate, loggedUser);

        do {
            //TODO count for days of left
            countForDaysOff();

            //TODO count for weekends

            //TODO count for national holidays

            //TODO count for pending requests
        } while (false);

        return LocalDate.now();
    }

    private void countForDaysOff () {
        for (int i = 1; i <= this.daysOffCounter; i++) {
            this.dayOff.add(this.dayOff.get(this.dayOff.size() - 1).plusDays(i));
        }
    }

    private void initiateObjects (String firstDate, String loggedUser){
        this.dayOff.add(LocalDate.parse(firstDate));
        this.user = userRepository.findByEmail(loggedUser);
        this.daysOffCounter = user.getDaysOffLeft();
    }

    private boolean checkForWeekends(){
         return false;
    }

    private boolean checkForNationalHolidays(){
         return false;
    }

    private boolean checkForPendingHolidayRequests(){
         return false;
    }
}
