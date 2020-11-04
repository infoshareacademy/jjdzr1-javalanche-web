package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.DayOffDao;
import com.infoshareacademy.model.DayOff;
import javax.ejb.LocalBean;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@LocalBean
public class DayOffRepository extends DayOffDao {

    private static final Logger LOGGER = Logger.getLogger(DayOffRepository.class.getName());

    public DayOff findDaysOffByDayOffId(int id){
        return (DayOff) entityManager.createQuery("FROM DayOff where id LIKE :id").setParameter("id", id).getSingleResult();
    }

    public List<DayOff> findDaysOffIdByDayOff(LocalDate date){
        return (List<DayOff>) entityManager.createQuery("FROM DayOff where firstDay <= :date and lastDay >= :date").setParameter("date", date).getResultList();
    }

    public List<DayOff> findDaysOffByUserEmail(String email) {
        return entityManager.createQuery("FROM DayOff where user.email LIKE :email").setParameter("email", email).getResultList();
    }

    public List<DayOff> findPendingHolidayRequests() {
        List<DayOff> dayOffs = new ArrayList<>();
        try{
            dayOffs = entityManager.createQuery("FROM DayOff where isAccepted = FALSE").getResultList();
            return dayOffs;
        } catch (Exception e){
            return dayOffs;
        }
    }

    public DayOff getDayOffById(Integer id){
        return entityManager.find(DayOff.class, id);
    }

    public List<DayOff> getHolidayRequestsWithinTeam(Integer teamId){
        return (List<DayOff>) entityManager.createQuery("FROM DayOff where id like :teamId and user.levelOfAccess = 1").getResultList();
    }

    public List<DayOff> getOverlappingHolidays(DayOff dayOff){
        return (List<DayOff>) entityManager.createQuery("FROM DayOff  where user.email like :email and (firstDay between :firstDay and :lastDay or lastDay between :firstDay and :lastDay)")
                .setParameter("email", dayOff.getUser().getEmail())
                .setParameter("firstDay", dayOff.getFirstDay())
                .setParameter("lastDay", dayOff.getLastDay())
                .getResultList();
    }

    public void uploadDaysOffForNewYear(){
        entityManager.createQuery("UPDATE User set daysOffLeft = daysOffLeft + 26").executeUpdate();
    }
}

