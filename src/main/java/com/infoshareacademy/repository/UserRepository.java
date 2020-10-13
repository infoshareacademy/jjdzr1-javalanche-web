package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.UserDao;
import com.infoshareacademy.model.User;

import javax.ejb.LocalBean;
import javax.persistence.NoResultException;
import java.util.logging.Logger;

@LocalBean
public class UserRepository extends UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    public User findByEmail(String email) {
        User user = null;
        try {
            user = (User) entityManager.createQuery("from User where email like :email").setParameter("email", email).getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return user;
    }

    public User findById(int id) {
        User user = null;
        try {
            user = (User) entityManager.createQuery("from User where id like :id").setParameter("id", id).getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return user;
    }



}
