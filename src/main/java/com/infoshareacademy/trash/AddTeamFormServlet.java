package com.infoshareacademy.trash;

import com.infoshareacademy.service.FormsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestScoped
@WebServlet("/addTeamForm")
public class AddTeamFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addTeamFormHandler(req);
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

    private void addTeamFormHandler(HttpServletRequest req) {
        String teamName = req.getParameter("addTeamName");
        String assignedTeamLeaderUsername = req.getParameter("assignTeamLeaderToGroup");
        boolean submissionStatus = formsService.addTeamFormInputHandler(teamName, assignedTeamLeaderUsername);
        if(submissionStatus){
            req.getSession().setAttribute("teamModificationStatus", "Team added successfully.");
        } else {
            req.getSession().setAttribute("teamModificationStatus", "System error. Adding team unsuccessful.");
        }
    }
}