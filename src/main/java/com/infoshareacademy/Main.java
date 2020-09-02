package com.infoshareacademy;

import com.infoshareacademy.api.HolidaysJsonData;

public class Main {

    public static void main(String[] args) {
        System.out.println("jAvalanche");

        HolidaysJsonData.returnOnlyHolidaysAsList().stream().forEach(System.out::println);

    }

}
