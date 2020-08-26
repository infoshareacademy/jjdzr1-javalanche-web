package com.infoshareacademy.model;

import java.time.LocalDate;
import java.util.Objects;

public class DayOff {
    private int id;
    private LocalDate startDay;
    private LocalDate endDay;
    private int idOfUser;
    private int daysOffSum;

    public DayOff(int id, LocalDate startDay, LocalDate endDay, int idOfUser, int daysOffSum) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.idOfUser = idOfUser;
        this.daysOffSum = daysOffSum;
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

    public int getDaysOffSum() {
        return daysOffSum;
    }

    public void setDaysOffSum(int daysOffSum) {
        this.daysOffSum = daysOffSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
                ", daysOffSum=" + daysOffSum +
                '}';
    }
}
