package com.infoshareacademy.service;


import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

@LocalBean
@Transactional
public class FormsService {


    @Inject
    private UserRepository userRepository;

    @Inject
    private DayOffRepository dayOffRepository;

    @Inject
    private DayOffService dayOffService;


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

    public void deleteHolidayRequestFormInputHandler(int requestToDeleteId){
        dayOffRepository.getAll().forEach(o -> {if(o.getId() == requestToDeleteId){dayOffRepository.delete(o);}});
    }

    public void placeHolidayRequestInputHandler(LocalDate firstDay, LocalDate lastDay, String email){
        DayOff dayOff = new DayOff();
        dayOff.setFirstDay(firstDay);
        dayOff.setLastDay(lastDay);
        dayOff.setAccepted(false);
        dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(firstDay, lastDay));
        dayOff.setUser(userRepository.findByEmail(email));
        dayOffRepository.create(dayOff);
    }

}
