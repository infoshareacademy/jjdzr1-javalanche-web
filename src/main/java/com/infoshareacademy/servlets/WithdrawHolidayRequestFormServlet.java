package com.infoshareacademy.servlets;

import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.FormsService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/withdrawHolidayRequestForm")
public class WithdrawHolidayRequestFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        withdrawHolidayRequestFormHandler(req);
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

    private void withdrawHolidayRequestFormHandler(HttpServletRequest req) {
        int holidayRequestIdToDelete = Integer.parseInt(req.getParameter("selectedHolidayRequestToDelete"));
        boolean submissionStatus = formsService.deleteHolidayRequestFormInputHandler(holidayRequestIdToDelete);
        if(submissionStatus){
            req.getSession().setAttribute("holidaysModificationStatus", "Holiday request withdrawn successfully");
        } else {
            req.getSession().setAttribute("holidaysModificationStatus", "System error. Holiday request withdrawn was unsuccessful.");
        }
    }
}