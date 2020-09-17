package com.infoshareacademy.service;

import com.infoshareacademy.repository.UserRepository;
import javax.inject.Inject;
import java.util.logging.Logger;

public class ValidationService {

    @Inject
    private UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(ValidationService.class.getName());

    public boolean isAuthenticated(String email, String password){
        boolean isAuthenticated = false;
        if (userRepository.findByEmail(email).getPassword().equals(password)){
            isAuthenticated = true;
        }
        return isAuthenticated;
    }

}
