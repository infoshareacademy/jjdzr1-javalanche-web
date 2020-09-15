package com.infoshareacademy.DTO;

import com.infoshareacademy.model.User;
import java.time.LocalDate;
import java.util.List;

public class DayOffDto {

    private LocalDate firstDay;
    private LocalDate lastDay;
    private User user;
    private List<LocalDate> listOfDays;

    public DayOffDto(LocalDate firstDay, LocalDate lastDay, User user, List<LocalDate> listOfDays) {
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.user = user;
        this.listOfDays = listOfDays;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDate lastDay) {
        this.lastDay = lastDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LocalDate> getListOfDays() {
        return listOfDays;
    }

    public void setListOfDays(List<LocalDate> listOfDays) {
        this.listOfDays = listOfDays;
    }
}
