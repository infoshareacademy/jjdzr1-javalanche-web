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
import java.util.logging.Logger;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Inject
    private CalendarService calendarService;
    @Inject
    private UserService userService;
    @Inject
    private DayOffService dayOffService;

    private static final Logger logger = Logger.getLogger(TestServlet.class.getName());

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
        req.setAttribute("calendarView", calendarService.calendarView(130));
        req.setAttribute("users", userService.getAll());
        req.setAttribute("map", dayOffService.mapUsersWithDaysOff());
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/main");
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }
}
