package com.infoshareacademy.service;

import com.infoshareacademy.DAO.UserDao;
import com.infoshareacademy.DTO.DayOffDto;
import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;

import javax.ejb.Local;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Local
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private SecurePasswordService securePasswordService;

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

    public List<UserDto> createListOfTeamLeadersWithTeam(){
        List<UserDto> teamLeadersWithTeamContainer = new ArrayList<>();
        try{
            teamLeadersWithTeamContainer = getAll().stream().filter(user -> user.isTeamLeader()).collect(Collectors.toList());
            return teamLeadersWithTeamContainer;
        }
        catch (Exception e){
            return teamLeadersWithTeamContainer;
        }
    }

    public List<UserDto> createListOfEmployeesInThisTeam(String loggedTeamLeader){
        try{
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

    public List<UserDto> createListOfEmployeesInAnyTeam(){
    try{
        List<UserDto> usersInAnyTeam = new ArrayList<>();
        usersInAnyTeam = getAll().stream()
                .filter(user -> user.getTeam()!=null & user.getLevelOfAccess()==1)
                .collect(Collectors.toList());
        return usersInAnyTeam;
        }
        catch (Exception e){
            List<UserDto> empty = new ArrayList<>();
            return empty;
        }
    }

    public boolean isEmptyDatabase(){
        return userRepository.getAll().isEmpty();
    }

    public void fillDefaultUser(){

        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setEmail("admin@admin.pl");
        user.setLevelOfAccess(3);
        user.setDaysOffLeft(26);
        user.setPassword(securePasswordService.encryptor("Admin"));
        userRepository.create(user);
    }

    public void setDaysOffLeft(DayOff dayOff){
        User user = dayOff.getUser();
        user.setDaysOffLeft(user.getDaysOffLeft() - dayOff.getListOfDays().size());
        userRepository.update(user);
    }
}
