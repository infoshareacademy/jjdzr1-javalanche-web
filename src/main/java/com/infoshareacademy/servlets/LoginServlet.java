package com.infoshareacademy.servlets;

import com.infoshareacademy.model.User;
import com.infoshareacademy.service.UserDaoService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    UserDaoService userDaoService;

    private User loggedUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session;

        if (isAuthenticated(username, password)) {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/main");

            session = req.getSession();
            session.setAttribute("username", username);

            view.forward(req, resp);

        } else {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/login");
            view.forward(req, resp);
        }
    }


    private boolean isAuthenticated(String username, String password){
        boolean isAuthenticated = false;
        for (User user: userDaoService.getAll()) {
            if (user.getEmail().equals(username) && user.getPassword().equals(password)){
                isAuthenticated = true;
                setLoggedUser(user);
                break;
            }
        }
        return isAuthenticated;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
