package com.infoshareacademy.servlets;

import com.infoshareacademy.service.DayOffDaoService;
import com.infoshareacademy.service.UserDaoService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    @Inject
    UserDaoService userDaoService;
    @Inject
    DayOffDaoService dayOffDaoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        req.setAttribute("userDaoService", userDaoService);
        req.setAttribute("dayOffDaoService", dayOffDaoService);

        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/main.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }

        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        req.setAttribute("userDaoService", userDaoService);
        req.setAttribute("dayOffDaoService", dayOffDaoService);

        HttpSession session = req.getSession();

        System.out.println("\n\n\n\n");
        System.out.println(session.getAttribute("username"));
        System.out.println("\n\n\n\n");

        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/main.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }

        view.forward(req, resp);
    }
}
