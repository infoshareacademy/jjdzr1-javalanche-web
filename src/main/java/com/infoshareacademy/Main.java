package com.infoshareacademy;


import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import com.infoshareacademy.configurations.PropertiesReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("jAvalanche");


        List<Holidays> holidays = HolidaysJsonData.readDataFromJsonFile().getServerResponse().getHolidays();
        holidays.stream().forEach(s -> System.out.println(s));

        System.out.println(new PropertiesReader().getDateFormat());

        PropertiesReader propertiesReader = new PropertiesReader();
        propertiesReader.getDateFormat();

    }

}
