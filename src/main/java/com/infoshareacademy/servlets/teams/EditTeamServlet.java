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

@WebServlet("/editteam")
public class EditTeamServlet extends HttpServlet {

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
            String teamName = req.getParameter("editedName");
            String teamLeadersEmail = req.getParameter("editTeamLeader");
            int editedTeamId = Integer.parseInt(req.getParameter("editedTeamId"));
            editTeam(teamName, teamLeadersEmail, editedTeamId);

            message = "edited successfully";
            status = true;
        } catch (Exception e){
            message = "edited unsuccessfully";
        }

        req.getSession().setAttribute("task", "Team");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }

    private void editTeam(String name, String teamLeadersEmail, int editedTeamId) {
        Team team = teamRepository.findById(editedTeamId);
        User newTeamLeader = userRepository.findByEmail(teamLeadersEmail);
        User oldTeamLeader = teamRepository.findById(editedTeamId).getTeamLeader();

        team.setName(name);
        team.setTeamLeader(newTeamLeader);
        newTeamLeader.setTeamLeader(true);
        newTeamLeader.setTeam(team);
        oldTeamLeader.setTeamLeader(false);
        oldTeamLeader.setTeam(null);

        userRepository.update(newTeamLeader);
        userRepository.update(oldTeamLeader);
        teamRepository.update(team);
    }

}
