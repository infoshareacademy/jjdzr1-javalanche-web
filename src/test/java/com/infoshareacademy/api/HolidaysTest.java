package com.infoshareacademy.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class HolidaysTest {

    private final String API_URL_LINK = "https://calendarific.com/api/v2/holidays?api_key=bad0ddcffc6542810f3cfb5749d38c63b5b7929a&country=PL&year=" + LocalDate.now().getYear();
    List<Holidays> listOfHolidaysToTest;
    Random randInt = new Random();

    @Before
    public void init() {
        this.listOfHolidaysToTest = HolidaysJsonData.readNationalHolidaysFromApiUrl(API_URL_LINK);
    }

    @Test
    public void checkIfMethodGetHolidayDateInLocalDateFormatReturnsCorrectFormat(){
        //given
        Holidays holiday = listOfHolidaysToTest.get(randInt.nextInt(listOfHolidaysToTest.size()));
        //when
        LocalDate holidayDate = holiday.getHolidayDateInLocalDateFormat();
        //then
        assertThat(holidayDate, instanceOf(LocalDate.class));
    }

}
