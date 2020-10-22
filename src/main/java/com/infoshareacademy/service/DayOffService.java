package com.infoshareacademy.service;

import com.infoshareacademy.DTO.DayOffDto;
import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@LocalBean
public class DayOffService {

    @Inject
    private DayOffRepository dayOffRepository;
    @Inject
    private UserService userService;

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

    public List<DayOffDto> pendingHolidayRequests(String email){
        return getByUserEmail(email).stream().filter(dayOff -> !dayOff.isAccepted()).collect(Collectors.toList());
    }

    private List<DayOffDto> mapDaysOffToDto(List<DayOff> dayOffs) {
        return dayOffs.stream()
                .map(dayOff -> new DayOffDto(dayOff.getId(), dayOff.getFirstDay(), dayOff.getLastDay(), dayOff.getUser(), dayOff.isAccepted(), dayOff.getListOfDays()))
                .collect(Collectors.toList());
    }

    public List<LocalDate> setListDaysWithoutWeekend(LocalDate firstDate, LocalDate lastDate){
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate date = firstDate;
        do {
            if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")){

            }
            else {
                dateList.add(date);
            }
            date = date.plusDays(1);
        } while (date.isBefore(lastDate) || date.isEqual(lastDate));
        return dateList;
    }

    public Map<String, List<String>> mapUsersWithAcceptedDaysOff(){
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

    public Map<String, List<String>> mapUsersWithNotAcceptedDaysOff(){
        Map<String, List<String>> map = new LinkedHashMap<>();
        for (UserDto user: userService.getAll()) {
            List<String> dates = new ArrayList<>();
            for (DayOffDto day: getByUserEmail(user.getEmail())) {
                if (!day.isAccepted()){
                    day.getListOfDays().forEach(localDate -> dates.add(localDate.getDayOfWeek()+"<br>"+localDate));
                }
            }
            map.put(user.getEmail(), dates);
        }
        return map;
    }
}
