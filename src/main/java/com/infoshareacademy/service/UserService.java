package com.infoshareacademy.service;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;
import javax.ejb.Local;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Local
public class UserService {

    @Inject
    private UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        List<UserDto> userDtos = mapUsersToDto(users);
        return userDtos;
    }

    private List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDaysOffLeft(), user.getLevelOfAccess()))
                .collect(Collectors.toList());
    }

    public UserDto getByEmail(String email){
        User user = userRepository.findByEmail(email);
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDaysOffLeft(), user.getLevelOfAccess());
    }
}
