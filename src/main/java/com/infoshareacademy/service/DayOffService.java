package com.infoshareacademy.service;

import com.infoshareacademy.DTO.DayOffDto;
import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.repository.DayOffRepository;
import javax.ejb.Local;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Local
public class DayOffService {

    @Inject
    private DayOffRepository dayOffRepository;

    public List<DayOffDto> getAll(){
        List<DayOff> dayOffs = dayOffRepository.getAll();
        List<DayOffDto> dayOffDtos = mapDaysOffToDto(dayOffs);
        return dayOffDtos;
    }

    private List<DayOffDto> mapDaysOffToDto(List<DayOff> dayOffs) {
        return dayOffs.stream()
                .map(dayOff -> new DayOffDto(dayOff.getFirstDay(), dayOff.getLastDay(), dayOff.getUser(), dayOff.getListOfDays()))
                .collect(Collectors.toList());
    }

    public List<LocalDate> setListDaysWithoutWeekend(LocalDate firstDate, LocalDate lastDate){
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate date = firstDate;
        dateList.add(date);
        do {
            date = date.plusDays(1);
            if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")){

            }
            else {
                dateList.add(date);
            }
        } while (date.isBefore(lastDate));
        return dateList;
    }
}
