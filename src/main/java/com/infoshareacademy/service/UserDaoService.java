package com.infoshareacademy.service;

import com.infoshareacademy.DAO.UserDAO;
import com.infoshareacademy.model.User;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Local
public class UserDaoService implements UserDAO, Serializable {

    @PersistenceContext
    EntityManager entityManager;

    public User getUserById(int id){
        User user = null;
        for (User u: getAll()) {
            if (u.getId() == id){
                user = u;
            }
        }
        return user;
    }

    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }
}
