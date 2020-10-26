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

@WebServlet("/deleteTeamForm")
public class DeleteTeamFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteTeamFormHandler(req);
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

    private void deleteTeamFormHandler(HttpServletRequest req) {
        int teamIdToDelete = Integer.parseInt(req.getParameter("selectedTeamIdToDelete"));
        boolean submissionStatus = formsService.deleteTeamFormInputHandler(teamIdToDelete);
        if(submissionStatus){
            req.getSession().setAttribute("teamModificationStatus", "Team deleted successfully.");
        } else {
            req.getSession().setAttribute("teamModificationStatus", "System error. Team deletion unsuccessful.");
        }
    }
}