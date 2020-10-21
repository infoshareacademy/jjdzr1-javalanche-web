package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.DayOffDao;
import com.infoshareacademy.model.DayOff;
import javax.ejb.LocalBean;
import java.util.List;
import java.util.logging.Logger;

@LocalBean
public class DayOffRepository extends DayOffDao {

    private static final Logger LOGGER = Logger.getLogger(DayOffRepository.class.getName());

    public DayOff findDaysOffByDayyOffId(int id){
        return (DayOff) entityManager.createQuery("FROM DayOff where id LIKE :id").setParameter("id", id).getSingleResult();
    }

    public List<DayOff> findDaysOffByUserEmail(String email) {
        return entityManager.createQuery("FROM DayOff where user.email LIKE :email").setParameter("email", email).getResultList();
    }

    public DayOff getDayOffById(Integer id){
        return entityManager.find(DayOff.class, id);
    }

}

