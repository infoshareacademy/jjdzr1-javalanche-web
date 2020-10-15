package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.NationalHolidayDao;
import com.infoshareacademy.DTO.NationalHolidayDto;
import com.infoshareacademy.model.NationalHoliday;

import javax.ejb.LocalBean;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    public List<NationalHolidayDto> parseNationalHolidaysToDto() {
        return super.getAll().stream()
                .map(holiday -> new NationalHolidayDto(
                        holiday.getId(),
                        holiday.getName(),
                        holiday.getDescription(),
                        holiday.getHolidayDate()))
                .collect(Collectors.toList());
    }

}

