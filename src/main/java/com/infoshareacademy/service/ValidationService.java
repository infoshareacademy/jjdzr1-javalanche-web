package com.infoshareacademy.service;

import com.infoshareacademy.DTO.UserDto;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import java.util.logging.Logger;

@LocalBean
public class ValidationService {

    @Inject
    private UserService userService;

    private static final Logger logger = Logger.getLogger(ValidationService.class.getName());

    public boolean isAuthenticated(String email, String password){
        boolean isAuthenticated = false;
        for (UserDto user: userService.getAll()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                isAuthenticated = true;
            }
        }
        return isAuthenticated;
    }

}
