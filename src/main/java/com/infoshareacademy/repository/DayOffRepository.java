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
//        dayOffList.add(new DayOff(1,LocalDate.of(2020,9,1),LocalDate.of(2020,9,3),1));
//        dayOffList.add(new DayOff(2,LocalDate.of(2020,9,5),LocalDate.of(2020,9,10),2));
//        dayOffList.add(new DayOff(3,LocalDate.of(2020,9,2),LocalDate.of(2020,9,4),3));
//        dayOffList.add(new DayOff(4,LocalDate.of(2020,9,14),LocalDate.of(2020,9,18),4));
//        dayOffList.add(new DayOff(5,LocalDate.of(2020,9,1),LocalDate.of(2020,9,23),5));
//        dayOffList.add(new DayOff(6,LocalDate.of(2020,10,20),LocalDate.of(2020,11,23),7));
//        dayOffList.add(new DayOff(7,LocalDate.of(2020,10,30),LocalDate.of(2020,12,31),8));
        setDayOffList(dayOffList);
    }

    @Override
    public String toString() {
        return "DayOffRepository{" +
                "dayOffList=" + dayOffList.get(1).toString() +
                '}';
    }
}

