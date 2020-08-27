package com.infoshareacademy.model;

import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int levelOfAccess;
    private int daysOffLeft;


    public User() {
    }

    public User(int id, String email, String password, String firstName, String lastName, int levelOfAccess, int daysOffLeft) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.levelOfAccess = levelOfAccess;
        this.daysOffLeft = daysOffLeft;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLevelOfAccess(int levelOfAccess) {
        this.levelOfAccess = levelOfAccess;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getLevelOfAccess() {
        return levelOfAccess;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", levelOfAccess=" + levelOfAccess +
                ", daysOffLeft=" + daysOffLeft +
                '}';
    }
}
