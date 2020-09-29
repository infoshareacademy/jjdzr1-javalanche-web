package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.TeamDao;
import com.infoshareacademy.model.Team;
import java.util.List;
import javax.ejb.LocalBean;
import java.util.logging.Logger;

@LocalBean
public class TeamRepository extends TeamDao {

    private static final Logger LOGGER = Logger.getLogger(DayOffRepository.class.getName());

    public List<Team> findByName(String name){
        return entityManager.createQuery("FROM Team where name like :name").setParameter("name", name).getResultList();
    }

    public Team findById(int id) {
        return (Team) entityManager.createQuery("FROM Team where id like :id").setParameter("id", id).getSingleResult();
    }

    public Team findByTeamLeader(String email){
        return (Team) entityManager.createQuery("from Team where teamLeader.email like :email").setParameter("email", email).getSingleResult();
    }
}
