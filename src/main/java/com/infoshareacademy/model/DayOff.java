package com.infoshareacademy.model;

import java.time.LocalDate;
import java.util.*;

public class DayOff {
    private int id;
    private LocalDate startDay;
    private LocalDate endDay;
    private int idOfUser;
    private List<LocalDate> listOfDays = new ArrayList<>();

    public DayOff() {
    }

    public DayOff(int id, LocalDate startDay, LocalDate endDay, int idOfUser) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.idOfUser = idOfUser;
        setListOfDays(startDay, endDay);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public int getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(int idOfUser) {
        this.idOfUser = idOfUser;
    }

    public List<LocalDate> getListOfDays() {
        return listOfDays;
    }

    public void setListOfDays(LocalDate startDay, LocalDate endDay) {
        List<LocalDate> listOfDays = new ArrayList<>();
        LocalDate date = startDay;

        do {
            if (date.getDayOfWeek().toString().equalsIgnoreCase("saturday") || date.getDayOfWeek().toString().equalsIgnoreCase("sunday")){
                date = date.plusDays(1);
            }
            else {
                listOfDays.add(date);
                date = date.plusDays(1);
            }
        } while (date.isBefore(endDay));

        this.listOfDays = listOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayOff)) return false;
        DayOff dayOff = (DayOff) o;
        return id == dayOff.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DayOff{" +
                "id=" + id +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", idOfUser=" + idOfUser +
                ", listOfDays=" + listOfDays +
                '}';
    }
}
