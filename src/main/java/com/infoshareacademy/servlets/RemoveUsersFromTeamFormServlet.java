package com.infoshareacademy.servlets;

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

@WebServlet("/removeUsersFromTeam")
public class RemoveUsersFromTeamFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeUsersFromTeamFormHandler(req);
        resp.sendRedirect(req.getContextPath() + "/teamForms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/teamForms.jsp");
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void removeUsersFromTeamFormHandler(HttpServletRequest req) {
        String[] employeesChosenForRemovalFromTeam = req.getParameterValues("selectedUsersToRemoveFromTeam");
        boolean submissionStatus = formsService.removeUsersFromTeamInputHandler(employeesChosenForRemovalFromTeam);
        if(submissionStatus){
            req.getSession().setAttribute("teamModificationStatus", "User(s) removed from a team successfully.");
        } else {
            req.getSession().setAttribute("teamModificationStatus", "System error. Removing user(s) from a team unsuccessful.");
        }
    }

}