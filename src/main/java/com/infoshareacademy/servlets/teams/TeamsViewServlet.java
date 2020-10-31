package com.infoshareacademy.servlets.teams;

import com.infoshareacademy.service.TeamService;
import com.infoshareacademy.service.UserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teams")
public class TeamsViewServlet extends HttpServlet {

    @Inject
    private TeamService teamsService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            req.setAttribute("teams", teamsService.getAll());
            req.setAttribute("admin", userService.getByEmail(req.getSession().getAttribute("username").toString()));
            req.setAttribute("teamLeaders", userService.getAvailableTeamLeaders());
            req.setAttribute("usersWithoutTeam", userService.createListOfEmployeesWithoutTeam());
            req.setAttribute("userWithTeam", userService.createListOfEmployeesInTeam(req.getSession().getAttribute("username").toString()));

            view = getServletContext().getRequestDispatcher("/teamsView.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }
}
