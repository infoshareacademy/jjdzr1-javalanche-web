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
    private UserRepository userRepository;


    public int loggedUsersLevelOfAccessRetriever(String email){
        return userRepository.findByEmail(email).getLevelOfAccess();
    }

    public void addUserFormInputDatabaseHandler(String username, String password, String firstName, String lastname, int daysOff, int levelOfAccess){
        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastname);
        user.setDaysOffLeft(daysOff);
        user.setLevelOfAccess(levelOfAccess);
        userRepository.create(user);
    }

    public void deleteUserFormInputHandler(int recordToDeleteId){
        userRepository.getAll().forEach(o -> {if(o.getId() == recordToDeleteId){userRepository.delete(o);}});
    }

}
