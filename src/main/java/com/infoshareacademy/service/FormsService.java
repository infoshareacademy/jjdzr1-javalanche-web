package com.infoshareacademy.service;


import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.Team;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.TeamRepository;
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
    private TeamRepository teamRepository;

    @Inject
    private DayOffService dayOffService;

    public int loggedUsersLevelOfAccessRetriever(String email){
        return userRepository.findByEmail(email).getLevelOfAccess();
    }

    public void addUserFormInputDatabaseHandler(User createdUser){
        userRepository.create(createdUser);
    }

    public void deleteUserFormInputHandler(int recordToDeleteId){
        userRepository.delete(userRepository.findById(recordToDeleteId));
    }

    public void addTeamFormInputHandler(String teamName, String teamLeaderUsername){
        Team team = new Team();
        team.setName(teamName);
        team.setTeamLeader(userRepository.findByEmail(teamLeaderUsername));
        team.setUserEmail(null);
        User user = userRepository.findByEmail(teamLeaderUsername);
        user.setTeamLeader(true);
        user.setTeam(team);
        userRepository.update(user);
    }

    public void deleteTeamFormInputHandler(int teamId){
        User loggedTeamLeader = userRepository.findById(teamRepository.findById(teamId).getTeamLeader().getId());
        loggedTeamLeader.setTeam(null);
        loggedTeamLeader.setTeamLeader(false);
        userRepository.update(loggedTeamLeader);
        Team team = teamRepository.findById(teamId);
        team.setTeamLeader(null);
        teamRepository.delete(teamRepository.findById(teamId));
    }

    public void placeHolidayRequestInputHandler(LocalDate firstDay, LocalDate lastDay, String email){
        DayOff dayOff = new DayOff();
        dayOff.setFirstDay(firstDay);
        dayOff.setLastDay(lastDay);
        dayOff.setAccepted(true);
        dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(firstDay, lastDay));
        dayOff.setUser(userRepository.findByEmail(email));
        dayOffRepository.create(dayOff);
    }

    public void deleteHolidayRequestFormInputHandler(int requestToDeleteId){
        dayOffRepository.delete(dayOffRepository.findDaysOffByUserId(requestToDeleteId));
    }

}
