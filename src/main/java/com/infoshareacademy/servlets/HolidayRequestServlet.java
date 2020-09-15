package com.infoshareacademy.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/holidayrequest")
public class HolidayRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/holidayrequest.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/holidayrequest.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req,resp);
    }

}
