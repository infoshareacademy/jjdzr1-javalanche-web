package com.infoshareacademy.service;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class FormsServiceTest {

    FormsService formsService;

    @Before
    public void init() {
        formsService = new FormsService();
        this.formsService.userRepository = mock(UserRepository.class);
        this.formsService.dayOffRepository = mock(DayOffRepository.class);
        this.formsService.teamRepository = mock(TeamRepository.class);
        this.formsService.dayOffService = mock(DayOffService.class);
    }

    @Test
    public void addUserFormHandlerMethodTest() {
        //given
        User user = mock(User.class);
        //when
        formsService.addUserFormInputDatabaseHandler(user);
        //then
        verify(formsService.userRepository).create(anyObject());
    }

    @Test
    public void deleteUserFormHandlerMethodTest() {
        //given
        //when
        formsService.deleteUserFormInputHandler(anyInt());
        //then
        verify(formsService.userRepository).delete(anyObject());
    }


    @Test
    public void addTeamFormInputHandlerMethodTest() {
        //given
        User testUser = mock(User.class);
        //when
        when(formsService.userRepository.findByEmail(anyString())).thenReturn(testUser);
        formsService.addTeamFormInputHandler(anyString(), "test");
        //then
        verify(formsService.userRepository).update(anyObject());
    }

    @Test
    public void holidayRequestDecisionFormInputHandlerTestIfAccepted() {
        //given
        DayOff dayOff = mock(DayOff.class);
        //when
        when(formsService.dayOffRepository.findDaysOffByDayyOffId(anyInt())).thenReturn(dayOff);
        formsService.holidayRequestDecisionFormInputHandler(anyInt(), true);
        //then
        verify(formsService.dayOffRepository).update(anyObject());
    }

    @Test
    public void holidayRequestDecisionFormInputHandlerTestIfRejected() {
        //given
        DayOff dayOff = mock(DayOff.class);
        //when
        when(formsService.dayOffRepository.findDaysOffByDayyOffId(anyInt())).thenReturn(dayOff);
        formsService.holidayRequestDecisionFormInputHandler(anyInt(), false);
        //then
        verify(formsService.dayOffRepository).delete(anyObject());
    }

    @Test
    public void placeHolidayRequestInputHandlerTestIf() {
        //given
        User testUser = mock(User.class);
        List<LocalDate> mockList = mock(List.class);
        LocalDate date = LocalDate.now();
        String mockString = "testString";
        //when
        when(formsService.dayOffService.setListDaysWithoutWeekend(any(), any())).thenReturn(mockList);
        when(formsService.userRepository.findByEmail(anyString())).thenReturn(testUser);
        formsService.placeHolidayRequestInputHandler(date, date, mockString);
        //then
        verify(formsService.dayOffRepository).create(anyObject());
    }

    @Test
    public void deleteHolidayRequestFormInputHandlerTest() {
        //given
        DayOff dayOff = mock(DayOff.class);
        //when
        when(formsService.dayOffRepository.findDaysOffByDayyOffId(anyInt())).thenReturn(dayOff);
        formsService.deleteHolidayRequestFormInputHandler(anyInt());
        //then
        verify(formsService.dayOffRepository).delete(anyObject());
    }
}
