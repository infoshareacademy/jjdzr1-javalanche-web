package com.infoshareacademy.repository;

import com.infoshareacademy.model.DayOff;
import java.time.LocalDate;
import java.util.ArrayList;

public class DayOffRepository {
    private ArrayList<DayOff> dayOffList = new ArrayList<>();

    public ArrayList<DayOff> getDayOffList() {
        return dayOffList;
    }

    public void setDayOffList(ArrayList<DayOff> dayOffList) {
        this.dayOffList = dayOffList;
    }
    public void fillDayOffList(){
        ArrayList<DayOff> dayOffList = new ArrayList<>();
        dayOffList.add(new DayOff(1,LocalDate.of(2020,9,1),LocalDate.of(2020,9,2),1,2));
        dayOffList.add(new DayOff(2,LocalDate.of(2020,9,5),LocalDate.of(2020,9,8),3,4));
        dayOffList.add(new DayOff(3,LocalDate.of(2020,9,2),LocalDate.of(2020,9,6),5,5));
        setDayOffList(dayOffList);
    }
}
