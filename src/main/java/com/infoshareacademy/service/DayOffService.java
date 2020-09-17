package com.infoshareacademy.service;

import com.infoshareacademy.DTO.DayOffDto;
import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.repository.DayOffRepository;
import javax.ejb.Local;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Local
public class DayOffService {

    @Inject
    private DayOffRepository dayOffRepository;
    @Inject
    private UserService userService;

    private static final Logger logger = Logger.getLogger(DayOffService.class.getName());

    public List<DayOffDto> getAll(){
        List<DayOff> dayOffs = dayOffRepository.getAll();
        List<DayOffDto> dayOffDtos = mapDaysOffToDto(dayOffs);
        return dayOffDtos;
    }

    public List<DayOffDto> getByUserEmail(String email){
        List<DayOff> dayOffs = dayOffRepository.findDaysOffByUserEmail(email);
        List<DayOffDto> dayOffDtos = mapDaysOffToDto(dayOffs);
        return dayOffDtos;
    }

    private List<DayOffDto> mapDaysOffToDto(List<DayOff> dayOffs) {
        return dayOffs.stream()
                .map(dayOff -> new DayOffDto(dayOff.getFirstDay(), dayOff.getLastDay(), dayOff.getUser(), dayOff.isAccepted(), dayOff.getListOfDays()))
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

    public Map<String, List<String>> mapUsersWithDaysOff(){
        Map<String, List<String>> map = new LinkedHashMap<>();
        for (UserDto user: userService.getAll()) {
            List<String> dates = new ArrayList<>();
            for (DayOffDto day: getByUserEmail(user.getEmail())) {
                if (day.isAccepted()){
                    day.getListOfDays().forEach(localDate -> dates.add(localDate.getDayOfWeek()+"<br>"+localDate));
                }
            }
            map.put(user.getEmail(), dates);
        }
        return map;
    }
}
