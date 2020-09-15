package com.infoshareacademy.service;

import com.infoshareacademy.DAO.DayOffDAO;
import com.infoshareacademy.model.DayOff;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Local
public class DayOffDaoService implements DayOffDAO, Serializable {

    @PersistenceContext
    EntityManager entityManager;

    public List<DayOff> getAll() {
        return entityManager.createQuery("FROM DayOff", DayOff.class).getResultList();
    }

    public List<DayOff> getDaysOffByUserId(int id){
        return entityManager.createQuery("FROM DayOff where user.id LIKE :id").setParameter("id", id).getResultList();
    }

    @Transactional
    public void create(DayOff dayOff) {
        entityManager.persist(dayOff);
    }

    @Transactional
    public void update(DayOff dayOff) {
        entityManager.merge(dayOff);
    }

    @Transactional
    public void delete(DayOff dayOff) {
        entityManager.remove(dayOff);
    }

    public List<LocalDate> dates(LocalDate startDate, LocalDate endDate){
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate date = startDate;
        dateList.add(date);
        do {
            date = date.plusDays(1);
            if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")){

            }
            else {
                dateList.add(date);
            }
        } while (date.isBefore(endDate));
        return dateList;
    }
}
