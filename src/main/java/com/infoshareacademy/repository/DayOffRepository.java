package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.DayOffDao;
import com.infoshareacademy.model.DayOff;
import javax.ejb.LocalBean;
import java.util.List;
import java.util.logging.Logger;

@LocalBean
public class DayOffRepository extends DayOffDao {

    private static final Logger LOGGER = Logger.getLogger(DayOffRepository.class.getName());

    public List<DayOff> findDaysOffByUserId(int id){
        return entityManager.createQuery("FROM DayOff where user.id LIKE :id").setParameter("id", id).getResultList();
    }

    public List<DayOff> findDaysOffByUserEmail(String email) {
        return entityManager.createQuery("FROM DayOff where user.email LIKE :email").setParameter("email", email).getResultList();
    }
}

