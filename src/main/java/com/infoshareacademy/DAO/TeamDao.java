package com.infoshareacademy.DAO;

import com.infoshareacademy.model.Team;

import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@LocalBean
public class TeamDao implements DAO<Team> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<Team> getAll() {
        return entityManager.createQuery("FROM Team", Team.class).getResultList();
    }

    @Override
    public void create(Team team) {
        entityManager.persist(team);
    }

    @Override
    public void update(Team team) {
        entityManager.merge(team);
    }

    @Override
    public void delete(Team team) {
        entityManager.remove(entityManager.contains(team) ? team : entityManager.merge(team));
    }
}
