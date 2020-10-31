package com.infoshareacademy.servlets.management;

import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.TeamService;
import com.infoshareacademy.service.UserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/management")
public class ManagementFormsServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private DayOffService dayOffService;

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
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/management.jsp");
            setAttributes(req, session);
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
        session.setAttribute("managementModificationStatus", "");
    }

    private void setAttributes(HttpServletRequest req, HttpSession session){
        req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));
        req.setAttribute("users", userService.getAll());
        req.setAttribute("daysOffRequests", dayOffService.pendingHolidayRequests(session.getAttribute("username").toString()));
        req.setAttribute("usersWithoutTeam", userService.createListOfEmployeesWithoutTeam());
        req.setAttribute("teamLeadersWithoutTeam", userService.createListOfTeamLeadersWithoutTeam());
        req.setAttribute("teamsList", teamService.getAll());
        req.setAttribute("loggedUser", userService.getByEmail(session.getAttribute("username").toString()));
        req.setAttribute("holidayRequests", dayOffService.getAll());
    }







    







}
