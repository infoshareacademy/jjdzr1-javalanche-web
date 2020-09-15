package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.DayOffDao;
import com.infoshareacademy.model.DayOff;

import javax.ejb.LocalBean;
import java.util.List;

@LocalBean
public class DayOffRepository extends DayOffDao {

    public List<DayOff> findDaysOffByUserId(int id){
        return entityManager.createQuery("FROM DayOff where user.id LIKE :id").setParameter("id", id).getResultList();
    }
}

