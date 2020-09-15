package com.infoshareacademy.service;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;
import javax.ejb.Local;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Local
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        List<UserDto> userDtos = mapUsersToDto(users);
        return userDtos;
    }

    private List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getFirstName(), user.getLastName(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
