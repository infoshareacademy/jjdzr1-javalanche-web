package com.infoshareacademy.servlets.teams;

import com.infoshareacademy.DTO.UserDto;
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
        //FIXME does work, but throws null

        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            int assignedTeamLeader = Integer.parseInt(req.getParameter("removeTeamsLeader"));
            removeTeam(assignedTeamLeader);

            view = getServletContext().getRequestDispatcher("/teamsView.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void removeTeam(int teamLeaderId) {
        //FIXME doesn't work, removes user together with the team
        teamRepository.delete(teamRepository.findById(teamLeaderId));
    }

}
