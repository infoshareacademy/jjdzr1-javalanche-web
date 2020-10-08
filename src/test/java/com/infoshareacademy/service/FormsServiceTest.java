package com.infoshareacademy.service;

import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class FormsServiceTest {

    @Mock
    UserRepository userRepository;

    @Test
    void defaultTest(){
        //given
        UserRepository userReposito = new UserRepository();

        //when
        List<User> usersFromDatabase = userRepository.getAll();

        //then


    }
}
