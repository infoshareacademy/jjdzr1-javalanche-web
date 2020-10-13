package com.infoshareacademy.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class DayOffServiceTest {
    private final Integer DAYS = 30;

    @Test
    void setListDaysWithoutWeekend() {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        dateList.add(date);
        LocalDate lastDate = LocalDate.now().plusDays(DAYS);
        do {
            date = date.plusDays(1);
            if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")) {

            } else {
                dateList.add(date);
            }
        } while (date.isBefore(lastDate));

        Assertions.assertThat(dateList)
                .isNotNull()
                .hasSizeGreaterThan(0)
                .hasSizeLessThan(DAYS)
                .doesNotHaveDuplicates();
    }
}