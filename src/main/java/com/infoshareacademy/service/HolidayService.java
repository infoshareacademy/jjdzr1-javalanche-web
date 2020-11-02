package com.infoshareacademy.service;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HolidayService {

    @Inject
    private DayOffRepository dayOffRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private DayOffService dayOffService;

    public void setNewHolidayRequest(LocalDate startDay, LocalDate endDay, HttpServletRequest req) {

        User user = userRepository.findByEmail(req.getSession().getAttribute("username").toString());
        DayOff dayOff = prepareDayOff(startDay, endDay, user);
        List<DayOff> overlappingRequests = dayOffRepository.getOverlappingHolidays(dayOff);

        if (overlappingRequests.size() > 0) {
            int numberOfDaysOffRemainingToAdd = appendUsersHolidayRequests(user, dayOff, overlappingRequests);
            user.setDaysOffLeft((int) (user.getDaysOffLeft() - dayOff.getListOfDays().size() + numberOfDaysOffRemainingToAdd));
            userRepository.update(user);
            clearOverlappingHolidayRequests(overlappingRequests);
        } else {
            user.setDaysOffLeft((int) (user.getDaysOffLeft() - dayOff.getListOfDays().size()));
            userRepository.update(user);
            dayOffRepository.create(dayOff);
        }
    }

    private int appendUsersHolidayRequests(User user, DayOff dayOff, List<DayOff> overlappingRequests) {
        List<LocalDate> listOfDaysOff = new ArrayList<>();

        overlappingRequests
                .forEach(entry -> listOfDaysOff.addAll(entry.getListOfDays()));

        listOfDaysOff
                .stream()
                .filter(date -> dayOff.getFirstDay().isAfter(date) | dayOff.getLastDay().isBefore(date));

        int numberOfDaysOffRemainingToAdd = listOfDaysOff.size();

        Set<DayOff> setOfDaysOffRequestsToAppend = user.getDaysOff();
        setOfDaysOffRequestsToAppend.removeAll(overlappingRequests.stream().collect(Collectors.toSet()));
        setOfDaysOffRequestsToAppend.add(dayOff);
        user.setDaysOff(setOfDaysOffRequestsToAppend);
        return numberOfDaysOffRemainingToAdd;
    }

    private void clearOverlappingHolidayRequests(List<DayOff> overlappingRequests) {
        for (DayOff entry : overlappingRequests) {
            entry.setListOfDays(null);
            entry.setUser(null);
            dayOffRepository.update(entry);
            dayOffRepository.delete(entry);
        }
    }

    private DayOff prepareDayOff(LocalDate startDay, LocalDate endDay, User user) {
        DayOff dayOff = new DayOff();
        dayOff.setUser(user);
        dayOff.setAccepted(user.getLevelOfAccess() == 3);

        dayOff.setFirstDay(startDay);
        dayOff.setLastDay(endDay);
        dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(startDay, endDay));
        return dayOff;
    }

}
