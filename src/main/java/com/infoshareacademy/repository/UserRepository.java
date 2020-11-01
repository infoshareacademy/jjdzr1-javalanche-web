package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.UserDao;
import com.infoshareacademy.model.Team;
import com.infoshareacademy.model.User;
import javax.ejb.LocalBean;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<User> findEmployeesInAnyTeam(){
        List users = new ArrayList<>();
        try {
            users = entityManager.createQuery("from User where team != null and levelOfAccess = 1").getResultList();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return users;
    }

    public List<User> findEmployeesInTeam(String email){
        List users = new ArrayList<>();
        try {
            users = entityManager.createQuery("from User where team.teamLeader.email = :email and levelOfAccess = 1").setParameter("email", email).getResultList();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return users;
    }

    public List<User> findEmployeesWithoutTeam(){
        List users = new ArrayList<>();
        try {
            users = entityManager.createQuery("from User where team = null and levelOfAccess = 1").getResultList();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return users;
    }

    public List<User> findAvailableTeamLeader(){
        List users = new ArrayList<>();
        try {
            users = entityManager.createQuery("from User where isTeamLeader = FALSE and levelOfAccess = 2").getResultList();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return users;
    }

    public void removeUserFromTeam(Integer userId) {
        User user = findById(userId);
        entityManager.detach(user);
        user.setTeam(null);
        update(user);
    }
}
