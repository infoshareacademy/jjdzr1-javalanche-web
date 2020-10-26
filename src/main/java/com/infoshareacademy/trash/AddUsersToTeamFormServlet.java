package com.infoshareacademy.trash;

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

@WebServlet("/addUsersToTeamForm")
public class AddUsersToTeamFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addUsersToTeamFormHandler(req);
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

    private void addUsersToTeamFormHandler(HttpServletRequest req) {
        String loggedTeamLeader;
        if(req.getSession().getAttribute("levelOfAccess").toString().equals("2")){
            loggedTeamLeader = req.getSession().getAttribute("username").toString();
        } else {
            loggedTeamLeader = req.getParameter("assignUserToThisTeamLeader");
        }
        String[] employeesChosenForATeam = req.getParameterValues("selectedUsersForTeam");

        boolean submissionStatus = formsService.addUsersToTeamFormInputHandler(loggedTeamLeader, employeesChosenForATeam);
        if(submissionStatus){
            req.getSession().setAttribute("teamModificationStatus", "Team modification successful.");
        } else {
            req.getSession().setAttribute("teamModificationStatus", "System error. Modifying team unsuccessful.");
        }
    }


}