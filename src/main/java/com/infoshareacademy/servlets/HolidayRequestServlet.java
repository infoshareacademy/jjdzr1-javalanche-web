package com.infoshareacademy.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/holidayrequest")
public class HolidayRequestServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HolidayRequestServlet.class.getName());

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
            view = getServletContext().getRequestDispatcher("/holidayrequest.jsp");
        } else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req,resp);
    }
}
