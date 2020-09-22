package com.infoshareacademy.service;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;
import javax.ejb.Local;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Local
public class UserService {

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
        getAll().stream()
                .forEach(user -> {if (user.getTeam()==null & user.getLevelOfAccess()==1) {
                    usersWithoutTeam.add(user);
                }});
        return usersWithoutTeam;
    }

    public List<UserDto> createListOfTeamLeadersWithoutTeam(){
        List<UserDto> teamLeaders = new ArrayList<>();
        getAll().stream()
                .forEach(user -> {if (user.getTeam()==null & user.getLevelOfAccess()==2) {
                    teamLeaders.add(user);
                }});
        return teamLeaders;
    }
}
