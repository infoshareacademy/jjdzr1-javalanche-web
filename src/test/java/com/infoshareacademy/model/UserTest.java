package com.infoshareacademy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void setId() {
        User user = new User();
        user.setId(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    void setDaysOffLeft() {
        User user = new User();
        user.setDaysOffLeft(26);
        assertEquals(user.getDaysOffLeft(), 26);
    }

    @Test
    void setEmail() {
        User user = new User();
        user.setEmail("email");
        assertEquals(user.getEmail(), "email");
    }

    @Test
    void setPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals(user.getEmail(), "password");
    }

    @Test
    void setFirstName() {
        User user = new User();
        user.setFirstName("firstName");
        assertEquals(user.getFirstName(), "firstName");
    }

    @Test
    void setLastName() {
        User user = new User();
        user.setLastName("lastName");
        assertEquals(user.getLastName(), "lastName");
    }

    @Test
    void setLevelOfAccess() {
        User user = new User();
        user.setLevelOfAccess(1);
        assertEquals(user.getLevelOfAccess(), 1);
    }

    @Test
    void getId() {
        User user = new User();
        user.setId(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    void getEmail() {
        User user = new User();
        user.setEmail("email");
        assertEquals(user.getEmail(), "email");
    }

    @Test
    void getPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals(user.getEmail(), "password");
    }

    @Test
    void getFirstName() {
        User user = new User();
        user.setFirstName("firstName");
        assertEquals(user.getFirstName(), "firstName");
    }

    @Test
    void getLastName() {
        User user = new User();
        user.setLastName("lastName");
        assertEquals(user.getLastName(), "lastName");
    }

    @Test
    void getLevelOfAccess() {
        User user = new User();
        user.setLevelOfAccess(1);
        assertEquals(user.getLevelOfAccess(), 1);
    }

    @Test
    void getDaysOffLeft() {
        User user = new User();
        user.setDaysOffLeft(26);
        assertEquals(user.getDaysOffLeft(), 26);
    }

}