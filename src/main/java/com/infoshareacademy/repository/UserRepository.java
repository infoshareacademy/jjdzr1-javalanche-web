package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.UserDao;
import com.infoshareacademy.model.User;
import javax.ejb.LocalBean;
import java.util.logging.Logger;

@LocalBean
public class UserRepository extends UserDao {

    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());

    public User findByEmail(String email) {
        return (User) entityManager.createQuery("from User where email like :email").setParameter("email", email).getSingleResult();
    }
}
