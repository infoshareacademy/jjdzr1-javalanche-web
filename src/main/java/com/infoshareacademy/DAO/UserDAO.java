package com.infoshareacademy.DAO;

import com.infoshareacademy.model.User;
import javax.ejb.Local;

@Local
public interface UserDAO extends DAO<User> {

}