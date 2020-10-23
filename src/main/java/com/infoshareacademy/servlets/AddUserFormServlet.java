package com.infoshareacademy.servlets;

import com.infoshareacademy.model.User;
import com.infoshareacademy.service.*;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addUserForm")
public class AddUserFormServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Inject
    private SecurePasswordService securePasswordService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addUserFormHandler(req, resp);
        resp.sendRedirect(req.getContextPath() + "/userForms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/userForms.jsp");
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void addUserFormHandler(HttpServletRequest req, HttpServletResponse resp) {
        if(formsService.verifyIfPasswordsMatch(
                req.getParameter("addUserPassword"),
                req.getParameter("addUserRepeatPassword"))){
            User userToAdd = new User();
            userToAdd.setEmail(req.getParameter("addUserEmail"));
            userToAdd.setPassword(securePasswordService.encryptor(req.getParameter("addUserPassword")));

            userToAdd.setFirstName(req.getParameter("addUserFirstName"));
            userToAdd.setLastName(req.getParameter("addUserSurname"));
            userToAdd.setDaysOffLeft(Integer.parseInt(req.getParameter("addUserDaysOff")));
            userToAdd.setLevelOfAccess(Integer.parseInt(req.getParameter("levelOfAccess")));
            boolean submissionStatus = formsService.addUserFormInputDatabaseHandler(userToAdd);
            if(submissionStatus){
                req.getSession().setAttribute("userModificationStatus", "User added successfully.");
            } else {
                req.getSession().setAttribute("userModificationStatus", "System error. Adding user unsuccessful.");
            }
        } else {
            req.getSession().setAttribute("userModificationStatus", "Passwords do not match. Adding user unsuccessful.");
        }
    }
}
