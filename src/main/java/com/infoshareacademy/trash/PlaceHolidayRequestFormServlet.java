package com.infoshareacademy.trash;


import com.infoshareacademy.service.*;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

@WebServlet("/placeHolidayRequestForm")
public class PlaceHolidayRequestFormServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PlaceHolidayRequestFormServlet.class.getName());

    @Inject
    private FormsService formsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        placeHolidayRequestFormHandler(req, resp);
        resp.sendRedirect(req.getContextPath() + "/holidayForms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/holidayForms.jsp");
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void placeHolidayRequestFormHandler(HttpServletRequest req, HttpServletResponse resp) {
        LocalDate holidayFirstDay = LocalDate.parse(req.getParameter("holidayFirstDay"));
        LocalDate holidayLastDay = LocalDate.parse(req.getParameter("holidayLastDay"));
        if(holidayFirstDay.isBefore(holidayLastDay) || holidayFirstDay.isEqual(holidayLastDay)){
            boolean submissionStatus = formsService.placeHolidayRequestInputHandler(
                    holidayFirstDay,
                    holidayLastDay,
                    req.getSession().getAttribute("username").toString(),
                    Integer.parseInt(req.getSession().getAttribute("levelOfAccess").toString()));
            if(submissionStatus){
                req.getSession().setAttribute("holidayModificationStatus", "Holiday request placed successfully.");
            } else {
                req.getSession().setAttribute("holidayModificationStatus", "Holiday request placed unsuccessfully.");
            }
        } else {
            req.getSession().setAttribute("holidayModificationStatus", "Holiday's last date shouldn't be before first day.");
        }
        sendEmailWithHolidayRequest(req, resp);
    }

    private void sendEmailWithHolidayRequest(HttpServletRequest req, HttpServletResponse resp) {
        try {
            sendEmailMessage(req, resp);
        } catch (ServletException e) {
            LOGGER.warning(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendEmailMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String host = servletContext.getInitParameter("host");
        String port = servletContext.getInitParameter("port");
        String userName = servletContext.getInitParameter("userName");
        String password = servletContext.getInitParameter("password");

        HttpSession session = req.getSession();
        String recipient = "javalanche.isa@gmail.com";
        String subject = "New holiday request from "+session.getAttribute("username");
        String content = session.getAttribute("username")+
                "\n From " + req.getParameter("holidayFirstDay")+" to "+req.getParameter("holidayLastDay");

        try {
            EmailService.sendEmail(host, port, userName, password, recipient, subject, content);
        } catch (MessagingException e) {
            LOGGER.warning(e.getMessage());
        }
    }

}