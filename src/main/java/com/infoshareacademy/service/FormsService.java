package com.infoshareacademy.service;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.Team;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@LocalBean
@Transactional
public class FormsService {

    private static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    @Inject
    private UserRepository userRepository;

    @Inject
    private DayOffRepository dayOffRepository;

    @Inject
    private TeamRepository teamRepository;

    @Inject
    private DayOffService dayOffService;

    @Inject
    private UserService userService;

    @Inject
    private EmailService emailService;

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

    public void addUsersToTeamFormInputHandler(String loggedTeamLeaderUsername, String[] chosenEmployeesUsernames){
        User loggedTeamLeader = userRepository.findByEmail(loggedTeamLeaderUsername);
        List<String> updatedTeam = new ArrayList<>();
        updatedTeam.addAll(loggedTeamLeader.getTeam().getUserEmail());
        updatedTeam.addAll(Arrays.asList(chosenEmployeesUsernames));
        updatedTeam.stream().distinct().collect(Collectors.toList());

        loggedTeamLeader.getTeam().setUserEmail(updatedTeam);
        userRepository.update(loggedTeamLeader);

        teamRepository.update(loggedTeamLeader.getTeam());
        updatedTeam.forEach(employee -> userRepository.findByEmail(employee).setTeam(loggedTeamLeader.getTeam()));
    }

    public void removeUsersFromTeamInputHandler(String loggedTeamLeaderUsername, String[]employeesToRemoveFromTeam){
        try{
            User loggedTeamLeader = userRepository.findByEmail(employeesToRemoveFromTeam[0]).getTeam().getTeamLeader();

            List<String> chosenEmployeesUsernamesList = new ArrayList<>(Arrays.asList(employeesToRemoveFromTeam));

            List<String>remainingUsers = loggedTeamLeader.getTeam().getUserEmail();
            remainingUsers.removeAll(chosenEmployeesUsernamesList);

            loggedTeamLeader.getTeam().setUserEmail(remainingUsers);
            teamRepository.update(loggedTeamLeader.getTeam());
            chosenEmployeesUsernamesList.forEach(user -> userRepository.findByEmail(user).setTeam(null));
        } catch (Exception e){
            LOGGER.warning(() -> e.getMessage());
        }


    }

    public void holidayRequestDecisionFormInputHandler(int holidayRequestId, Boolean decision){
        DayOff dayOff = dayOffRepository.findDaysOffByDayOffId(holidayRequestId);
        if (decision){
            dayOff.setAccepted(true);
            dayOff.getUser().setDaysOffLeft(dayOff.getUser().getDaysOffLeft() - dayOff.getListOfDays().size());
            dayOffRepository.update(dayOff);
            userService.setDaysOffLeft(dayOff);
        }
        else {
            dayOffRepository.delete(dayOff);
        }
    }

    public void placeHolidayRequestInputHandler(LocalDate firstDay, LocalDate lastDay, String email, int levelOfAccess){
        DayOff dayOff = new DayOff();
        dayOff.setFirstDay(firstDay);
        dayOff.setLastDay(lastDay);
        if(levelOfAccess==3){
            dayOff.setAccepted(true);
        } else {
            dayOff.setAccepted(false);
        }
        dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(firstDay, lastDay));
        dayOff.setUser(userRepository.findByEmail(email));
        dayOffRepository.create(dayOff);

    }

    public void deleteHolidayRequestFormInputHandler(int requestToDeleteId){
        dayOffRepository.delete(dayOffRepository.findDaysOffByDayOffId(requestToDeleteId));
    }

    public void changeEmailInputHandler(String oldEmail, String newEmail){
        User userWithNewEmail = userRepository.findByEmail(oldEmail);
        userWithNewEmail.setEmail(newEmail);
        userRepository.update(userWithNewEmail);
    }

    public void changePasswordInputHandler(String email, String newPassword){
        User userWithNewPassword = userRepository.findByEmail(email);
        userWithNewPassword.setPassword(newPassword);
        userRepository.update(userWithNewPassword);
    }

    public boolean verifyIfPasswordsMatch(String password1, String password2){
        return password1.equals(password2);
    }
}
