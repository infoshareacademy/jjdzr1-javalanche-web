package com.infoshareacademy.service;

import com.infoshareacademy.repository.UserRepository;
import javax.inject.Inject;

public class ValidationService {

    @Inject
    private UserRepository userRepository;

    public boolean isAuthenticated(String email, String password){
        boolean isAuthenticated = false;
        if (userRepository.findByEmail(email).getPassword().equals(password)){
            isAuthenticated = true;
        }
        return isAuthenticated;
    }

}
