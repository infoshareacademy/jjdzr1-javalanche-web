package com.infoshareacademy.repository;

import com.infoshareacademy.model.User;

import java.util.ArrayList;

public class UserRepository {
    ArrayList<User> usersList = new ArrayList<>();

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public void fillUsersList(){
        ArrayList<User> usersList = new ArrayList<User>();

        usersList.add(new User(1, "jacek@wp.pl","1111","Jacek","Jackowski",1,26));
        usersList.add(new User(2, "Karol@wp.pl","2222","Karol","Kkarolowski",1,26));
        usersList.add(new User(3, "Tomek@wp.pl","3333","Tomek","Tomaszewski",1,26));
        usersList.add(new User(4, "Wojtek@wp.pl","4444","Wojtek","Wojciechowski",1,26));
        usersList.add(new User(5, "Marek@wp.pl","5555","Marek","Markowski",1,26));
        setUsersList(usersList);
    }

}
