package com.infoshareacademy.servlets;

import freemarker.template.TemplateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/forms")
public class FormsServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        Map<String, Object> dataModel = new HashMap<>();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();


        String email = req.getParameter("addUserEmail");
        String password = req.getParameter("addUserPassword");
        String firstName = req.getParameter("addUserFirstName");
        String lastName = req.getParameter("addUserSurname");

        String startDay = req.getParameter("startDay");
        String endDay = req.getParameter("endDate");


        RequestDispatcher view = getServletContext().getRequestDispatcher("/forms");

        String htmlRespone = "<html>";
        htmlRespone += "Your email is: " + email + "\n";
        htmlRespone += "Your password is: " + password + "\n";
        htmlRespone += "Your firstName is: " + firstName + "\n";
        htmlRespone += "Your lastName is: " + lastName + "\n";
        htmlRespone += "</html>";

        // return response
        writer.println(htmlRespone);

        logger.log(Level.INFO, email + " " + password + " " + firstName + " " + lastName);
        writer.write(email + " " + password + " " + firstName + " " + lastName);

        Map<String, Object> dataModel = new HashMap<>();

    }
}
