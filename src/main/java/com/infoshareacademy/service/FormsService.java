package com.infoshareacademy.service;

import com.infoshareacademy.DAO.UserDao;
import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.transaction.Transactional;

@LocalBean
@Transactional
public class FormsService {

    @Inject
    private UserDao userDao;

    @Inject
    private UserDto userDto;

    @Inject
    private UserRepository userRepository;

    public void addNewUser(final UserDto userDto) {
    }

    public int loggedUsersLevelOfAccessRetriever(String email){
        return userRepository.findByEmail(email).getLevelOfAccess();
    }

}
