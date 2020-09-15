package com.infoshareacademy.repository;


import com.infoshareacademy.DAO.UserDao;
import com.infoshareacademy.model.User;
import javax.ejb.LocalBean;

@LocalBean
public class UserRepository extends UserDao {

    public User findByEmail(String email) {
        return (User) entityManager.createQuery("from User where email like :email").setParameter("email", email).getSingleResult();
    }
}
