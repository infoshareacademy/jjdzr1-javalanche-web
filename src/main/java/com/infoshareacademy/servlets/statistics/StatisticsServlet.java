package com.infoshareacademy.servlets.statistics;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.service.TeamService;
import com.infoshareacademy.service.UserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {

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
        //            UserDto user = userService.getByEmail(String.valueOf(req.getSession().getAttribute("username")));
        //            req.setAttribute("teamLeader", user);
        //            req.setAttribute("team", userService.getUsersFromTeam(user.getEmail()));
        if (req.getSession().getAttribute("username") != null)
            view = getServletContext().getRequestDispatcher("/statisticsView/statistics.jsp");
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }
}
