package com.infoshareacademy.servlets;
import com.infoshareacademy.model.User;
import com.infoshareacademy.service.*;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/forms")
public class FormsServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

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
            view = getServletContext().getRequestDispatcher("/forms.jsp");
            setAttributes(req, session);
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, HttpSession session){
        req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));
        req.setAttribute("users", userService.getAll());
        req.setAttribute("daysOffRequests", dayOffService.pendingHolidayRequests(session.getAttribute("username").toString()));
        req.setAttribute("usersWithoutTeam", userService.createListOfEmployeesWithoutTeam());
        req.setAttribute("employeesInTeam", userService.createListOfEmployeesInThisTeam(session.getAttribute("username").toString()));
        req.setAttribute("teamLeadersWithoutTeam", userService.createListOfTeamLeadersWithoutTeam());
        req.setAttribute("teamsList", teamService.getAll());
        req.setAttribute("loggedUser", userService.getByEmail(session.getAttribute("username").toString()));
        req.setAttribute("holidayRequests", dayOffService.getAll());
    }







    







}
