package com.infoshareacademy.servlets.teams;

import com.infoshareacademy.model.Team;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/removeteam")
public class RemoveTeamServlet extends HttpServlet {

    @Inject
    private TeamRepository teamRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            performRequestWithValidation(req);
            view = getServletContext().getRequestDispatcher("/teams");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void performRequestWithValidation(HttpServletRequest req) {
        String task = "";
        String message = "";
        boolean status = false;

        try {
            int assignedTeamLeader = Integer.parseInt(req.getParameter("removeTeamsLeader"));
            removeTeam(assignedTeamLeader);

            message = "removed successfully";
            status = true;
        } catch (Exception e){
            message = "removed unsuccessfully";
        }

        req.getSession().setAttribute("task", "Team");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }

    private void removeTeam(int teamLeaderId) {
        User user = userRepository.findById(teamLeaderId);
        Team team = teamRepository.findByTeamLeader(user.getEmail());

        clearUsersTeamRelations(team);
        clearTeamLeader(user);
        removeTeam(team);
    }

    private void removeTeam(Team team) {
        team.setTeamLeader(null);
        team.setUserEmail(null);
        teamRepository.update(team);
        teamRepository.delete(team);
    }

    private void clearTeamLeader(User user) {
        user.setTeam(null);
        user.setTeamLeader(false);
        userRepository.update(user);
    }

    private void clearUsersTeamRelations(Team team) {
        List<String> usersInTeam = team.getUserEmail();
        for(String id : usersInTeam){
            User memberOfTeam = userRepository.findById(Integer.parseInt(id));
            memberOfTeam.setTeam(null);
            userRepository.update(memberOfTeam);
        }
    }

}
