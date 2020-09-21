package com.infoshareacademy.DTO;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int daysOffLeft;
    private int levelOfAccess;

    public UserDto(int id, String firstName, String lastName, String email, String password, int daysOffLeft, int levelOfAccess) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.daysOffLeft = daysOffLeft;
        this.levelOfAccess = levelOfAccess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    public int getLevelOfAccess() {
        return levelOfAccess;
    }

    public void setLevelOfAccess(int levelOfAccess) {
        this.levelOfAccess = levelOfAccess;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", daysOffLeft=" + daysOffLeft +
                ", levelOfAccess=" + levelOfAccess +
                '}';
    }

}
