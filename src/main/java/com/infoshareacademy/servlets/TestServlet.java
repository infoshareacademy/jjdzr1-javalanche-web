package com.infoshareacademy.servlets;

import com.infoshareacademy.service.CalendarService;
import com.infoshareacademy.service.DayOffService;
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

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Inject
    private CalendarService calendarService;
    @Inject
    private UserService userService;
    @Inject
    private DayOffService dayOffService;

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
        req.setAttribute("calendarView", calendarService.getCalendarView());
        req.setAttribute("users", userService.getAll());
        req.setAttribute("dayOff", dayOffService.getByUserEmail("jan@kowalski.pl"));
        req.setAttribute("map", dayOffService.mapUsersWithDaysOff());
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/test_jsp.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }
}
