package com.infoshareacademy.service;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.servlets.FormsServlet;

import javax.ejb.Local;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Local
public class UserService {

    private static final Logger logger = Logger.getLogger(FormsServlet.class.getName());

    @Inject
    private UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        List<UserDto> userDtos = mapUsersToDto(users);
        return userDtos;
    }

    private List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDaysOffLeft(), user.getLevelOfAccess(), user.isTeamLeader(), user.getTeam()))
                .collect(Collectors.toList());
    }

    public UserDto getByEmail(String email){
        User user = userRepository.findByEmail(email);
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDaysOffLeft(), user.getLevelOfAccess(), user.isTeamLeader(), user.getTeam());
    }


    public List<UserDto> createListOfEmployeesWithoutTeam(){
        List<UserDto> usersWithoutTeam = new ArrayList<>();
        getAll().forEach(user -> {if (user.getTeam()==null & user.getLevelOfAccess()==1) {
                    usersWithoutTeam.add(user);
                }});
        return usersWithoutTeam;
    }

    public List<UserDto> createListOfTeamLeadersWithoutTeam(){
        List<UserDto> teamLeaders = new ArrayList<>();
        getAll().forEach(user -> {if (!user.isTeamLeader() & user.getLevelOfAccess()==2) {
                    teamLeaders.add(user);
                }});
        return teamLeaders;
    }

    public List<UserDto> createListOfEmployeesInThisTeam(String loggedTeamLeader){
        try{
            int loggedTeamLeaderId = userRepository.findByEmail(loggedTeamLeader).getTeam().getId();
            List<String> usernamesInTeam = userRepository.findByEmail(loggedTeamLeader).getTeam().getUserEmail();
            List<UserDto> usersInThisTeam = new ArrayList<>();
            for(UserDto user : getAll()){
                for(String teamUsernames : usernamesInTeam){
                    if(user.getEmail().equals(teamUsernames)){
                        usersInThisTeam.add(user);
                    }
                }
            }
            return usersInThisTeam;
        }
        catch (Exception e){
            List<UserDto>empty = new ArrayList<>();
            return empty;
        }

    }
}
