package com.infoshareacademy.servlets;

import com.infoshareacademy.service.CalendarService;
import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.UserService;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    @Inject
    private CalendarService calendarService;
    @Inject
    private UserService userService;
    @Inject
    private DayOffService dayOffService;

    private static final Logger logger = Logger.getLogger(MainServlet.class.getName());

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
            view = getServletContext().getRequestDispatcher("/main.jsp");
            req.setAttribute("calendarView", calendarService.calendarView(30));
            req.setAttribute("users", userService.getAll());
            req.setAttribute("map", dayOffService.mapUsersWithDaysOff());
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }
}
