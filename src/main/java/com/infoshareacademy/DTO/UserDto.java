package com.infoshareacademy.DTO;

public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private int levelOfAccess;
    private int daysOffLeft;

    public UserDto(String firstName, String lastName, String email, int levelOfAccess, int daysOffLeft) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.levelOfAccess = levelOfAccess;
        this.daysOffLeft = daysOffLeft;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevelOfAccess() {
        return levelOfAccess;
    }

    public void setLevelOfAccess(int levelOfAccess) {
        this.levelOfAccess = levelOfAccess;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", levelOfAccess=" + levelOfAccess +
                ", daysOffLeft=" + daysOffLeft +
                '}';
    }
}
