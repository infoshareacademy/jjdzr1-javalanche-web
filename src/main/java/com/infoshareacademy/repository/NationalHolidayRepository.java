package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.NationalHolidayDao;
import com.infoshareacademy.model.NationalHoliday;
import com.infoshareacademy.model.User;

import javax.ejb.LocalBean;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.logging.Logger;

@LocalBean
public class NationalHolidayRepository extends NationalHolidayDao {

    private static final Logger LOGGER = Logger.getLogger(NationalHolidayRepository.class.getName());

    public NationalHoliday findByDate(LocalDate holidayDate) {
        NationalHoliday nationalHoliday = null;
        try {
            nationalHoliday = (NationalHoliday)
                    entityManager
                            .createQuery(
                                    "from NationalHoliday where holidayDate like :holidayDate")
                            .setParameter("holidayDate", holidayDate)
                            .getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return nationalHoliday;
    }

    public NationalHoliday findByName(String name) {
        NationalHoliday nationalHoliday = null;
        try {
            nationalHoliday = (NationalHoliday) entityManager.createQuery("from User where name like :name").setParameter("name", name).getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.warning(exception.getMessage());
        }
        return nationalHoliday;
    }


}

