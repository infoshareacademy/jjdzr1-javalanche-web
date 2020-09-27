package com.infoshareacademy.service;

import com.infoshareacademy.DTO.TeamDto;
import com.infoshareacademy.model.Team;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@LocalBean
public class TeamService {

    @Inject
    private TeamRepository teamRepository;

    public List<TeamDto> getAll(){
        List<Team> teams = teamRepository.getAll();
        List<TeamDto> teamDtos = mapTeamsToDto(teams);
        return teamDtos;
    }

    private List<TeamDto> mapTeamsToDto(List<Team> teams) {
        return teams.stream()
                .map(team -> new TeamDto(team.getId(), team.getName(), team.getUserEmail(), team.getTeamLeader()))
                .collect(Collectors.toList());
    }

}
