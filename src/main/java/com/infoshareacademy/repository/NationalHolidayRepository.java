package com.infoshareacademy.repository;

import com.infoshareacademy.DAO.NationalHolidayDao;

import javax.ejb.LocalBean;
import java.util.logging.Logger;

@LocalBean
public class NationalHolidayRepository extends NationalHolidayDao {

    private static final Logger LOGGER = Logger.getLogger(NationalHolidayRepository.class.getName());

}

