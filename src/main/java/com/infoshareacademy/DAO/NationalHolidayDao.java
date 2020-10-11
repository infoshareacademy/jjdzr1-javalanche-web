package com.infoshareacademy.DAO;

import com.infoshareacademy.model.NationalHoliday;

import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@LocalBean
public class NationalHolidayDao implements DAO<NationalHoliday>{

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<NationalHoliday> getAll() {
        return entityManager.createQuery("FROM NationalHoliday", NationalHoliday.class).getResultList();
    }

    @Override
    public void create(NationalHoliday nationalHoliday) {
        entityManager.persist(nationalHoliday);
    }

    @Override
    public void update(NationalHoliday nationalHoliday) {
        entityManager.merge(nationalHoliday);
    }

    @Override
    public void delete(NationalHoliday nationalHoliday) {
        entityManager.remove(nationalHoliday);
    }
}
