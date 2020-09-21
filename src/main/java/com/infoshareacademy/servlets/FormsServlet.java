package com.infoshareacademy.servlets;

import com.infoshareacademy.model.DayOff;
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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/forms")
public class FormsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FormsServlet.class.getName());

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
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/forms.jsp");
            req.setAttribute("levelOfAccess", userService.loggedUsersLevelOfAccessRetriever(
                    (String) session.getAttribute("username")
            ));
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }
}

/*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;

        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/forms.jsp");

            String username = (String) session.getAttribute("username");
            int levelOfAccess = userService.loggedUsersLevelOfAccessRetriever(username);
            session.setAttribute("levelOfAccess", levelOfAccess);

*//*            DayOff dayOff = new DayOff();
            dayOff.setUser(userRepository.findByEmail((String) session.getAttribute("username")));
            dayOff.setFirstDay(firstDay);
            dayOff.setLastDay(lastDay);
            dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(firstDay, lastDay));
            dayOffRepository.create(dayOff);*//*

        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }*/

