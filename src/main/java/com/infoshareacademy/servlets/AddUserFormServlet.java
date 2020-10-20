package com.infoshareacademy.servlets;

import com.infoshareacademy.model.User;
import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.FormsService;
import com.infoshareacademy.service.TeamService;
import com.infoshareacademy.service.UserService;
import com.infoshareacademy.servlets.FormsServlet;
import com.sun.net.httpserver.HttpServer;

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
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addUserFormHandler(req, resp);
        resp.sendRedirect(req.getContextPath() + "/forms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/forms.jsp");
            setAttributes(req, session);
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }


    private void setAttributes(HttpServletRequest req, HttpSession session){
        req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));

        req.setAttribute("users", userService.getAll());
    }


    private void addUserFormHandler(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("addUserPassword").equals(req.getParameter("addUserRepeatPassword"))){
            User userToAdd = new User();
            userToAdd.setEmail(req.getParameter("addUserEmail"));
            userToAdd.setPassword(req.getParameter("addUserPassword"));
            userToAdd.setPassword(req.getParameter("addUserRepeatPassword"));
            userToAdd.setFirstName(req.getParameter("addUserFirstName"));
            userToAdd.setLastName(req.getParameter("addUserSurname"));
            userToAdd.setDaysOffLeft(Integer.parseInt(req.getParameter("addUserDaysOff")));
            userToAdd.setLevelOfAccess(Integer.parseInt(req.getParameter("levelOfAccess")));
            formsService.addUserFormInputDatabaseHandler(userToAdd);
            req.setAttribute("addUserStatusIsSuccessful", true);
        } else {
            req.setAttribute("addUserStatusIsSuccessful", false);
        }
    }
}
