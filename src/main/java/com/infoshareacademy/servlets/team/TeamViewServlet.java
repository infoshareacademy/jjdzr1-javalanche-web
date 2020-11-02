package com.infoshareacademy.servlets.team;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;
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

@WebServlet("/team")
public class TeamViewServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private TeamService teamService;

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
//
//            UserDto teamLeader = userService.getByEmail(String.valueOf(req.getSession().getAttribute("username")));
//
//            req.setAttribute("teamLeader", teamLeader);
//            req.setAttribute("users", userService.getAll());
//
//            req.setAttribute("usersWithoutTeam", userService.createListOfEmployeesWithoutTeam());
//            req.setAttribute("employeesInTeam", userService.createListOfEmployeesInTeam(req.getSession().getAttribute("username").toString()));
//
            UserDto user = userService.getByEmail(String.valueOf(req.getSession().getAttribute("username")));
            req.setAttribute("teamLeader", user);
            req.setAttribute("team", userService.getUsersFromTeam(user.getEmail()));
            view = getServletContext().getRequestDispatcher("/teamView.jsp");

        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }
}
