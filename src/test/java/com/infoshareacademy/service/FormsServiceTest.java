package com.infoshareacademy.service;

import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FormsServiceTest {

    FormsService formsService;
    UserRepository userRepository;

    @Before
    public void init(){
        formsService = new FormsService();
        this.userRepository = mock(UserRepository.class);
        this.formsService.userRepository = mock(UserRepository.class);
        this.formsService.dayOffRepository = mock(DayOffRepository.class);
        this.formsService.teamRepository = mock(TeamRepository.class);
        this.formsService.dayOffService = mock(DayOffService.class);
    }

    @Test
    public void addUserFormHandlerMethodTest(){
        //given
        User user = mock(User.class);
        //when
        formsService.addUserFormInputDatabaseHandler(user);
        //then
        verify(formsService.userRepository).create(anyObject());
    }

    @Test
    public void deleteUserFormHandlerMethodTest(){
        //given

        //when
        formsService.deleteUserFormInputHandler(anyInt());
        //then
        verify(formsService.userRepository).delete(anyObject());
    }


}
