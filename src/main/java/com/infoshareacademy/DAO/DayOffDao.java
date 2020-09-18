package com.infoshareacademy.DAO;

import com.infoshareacademy.model.DayOff;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Local
public class DayOffDao implements DAO<DayOff> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<DayOff> getAll() {
        return entityManager.createQuery("from DayOff", DayOff.class).getResultList();
    }

    @Override
    public void create(DayOff dayOff) {
        entityManager.persist(dayOff);
    }

    @Override
    public void update(DayOff dayOff) {
        entityManager.merge(dayOff);
    }

    @Override
    public void delete(DayOff dayOff) {
        entityManager.remove(dayOff);
    }
}
