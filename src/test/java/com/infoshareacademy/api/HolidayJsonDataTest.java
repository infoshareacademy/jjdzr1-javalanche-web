package com.infoshareacademy.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HolidayJsonDataTest {

    private final String API_URL_LINK = "https://calendarific.com/api/v2/holidays?api_key=bad0ddcffc6542810f3cfb5749d38c63b5b7929a&country=PL&year=" + LocalDate.now().getYear();
    List<Holidays> listOfHolidaysToTest;
    Random randInt = new Random();

    @Before
    public void init() {
        this.listOfHolidaysToTest = HolidaysJsonData.readNationalHolidaysFromApiUrl(API_URL_LINK);
    }

    @Test
    public void checkIfReadNationalHolidaysFromApiUrlMethodWorksCorrectlyWithProperURL() {
        //given
        //when
        //then
        Assert.assertNotNull(listOfHolidaysToTest);
        Assert.assertNotEquals(0, listOfHolidaysToTest.size());
    }

    @Test
    public void checkIfReceivedListContainsNoNulls() {
        //given
        //when
        //then
        for (Holidays holiday : listOfHolidaysToTest) {
            Assert.assertNotNull(holiday.getName());
            Assert.assertNotNull(holiday.getDescription());
            Assert.assertNotNull(holiday.getCountry());
            Assert.assertNotNull(holiday.getHolidayDate());
            Assert.assertNotNull(holiday.getLocations());
            Assert.assertNotNull(holiday.getStates());
        }
    }

    @Test
    public void checkIfReceivedListIdFromCurrentYear() {
        //given
        //when
        //then
        Assert.assertEquals((int) listOfHolidaysToTest
                        .get(randInt.nextInt(listOfHolidaysToTest.size()))
                        .getHolidayDate().getHolidayDateTime().getYear()
                        ,LocalDate.now().getYear());
    }
}
