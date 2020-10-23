package com.infoshareacademy.servlets;

import com.infoshareacademy.model.User;
import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.FormsService;
import com.infoshareacademy.service.TeamService;
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

@WebServlet("/holidayRequestDecisionForm")
public class                                                                                                                        HolidayRequestDecisionFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Inject
    private UserService userService;

    @Inject
    private DayOffService dayOffService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        holidayRequestDecisionFormHandler(req);
        resp.sendRedirect(req.getContextPath() + "/holidayForms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/holidayForms.jsp");
            setAttributes(req, session);
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, HttpSession session){
        req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));
        req.setAttribute("employeesInTeam", userService.createListOfEmployeesInThisTeam(session.getAttribute("username").toString()));
        req.setAttribute("loggedUser", userService.getByEmail(session.getAttribute("username").toString()));
        req.setAttribute("holidayRequests", dayOffService.getAll());
    }

    private void holidayRequestDecisionFormHandler(HttpServletRequest req) {
        int chosenHolidayRequestId = Integer.parseInt(req.getParameter("selectedHolidayRequest"));
        Boolean isRequestAccepted = Boolean.parseBoolean(req.getParameter("holidayRequestVerdict"));
        formsService.holidayRequestDecisionFormInputHandler(chosenHolidayRequestId, isRequestAccepted);
    }
}