package com.infoshareacademy;

import com.infoshareacademy.service.DayOffService;

public class Main {

    public static void main(String[] args) {

        DayOffService dayOffService = new DayOffService();

        System.out.println(dayOffService.getAll());
    }
}
