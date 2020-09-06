package com.infoshareacademy.servlets;

import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//TODO authentication
        if (isAuthenticated(username, password)) {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/main.jsp");
            view.forward(req, resp);

        } else {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp");
            view.forward(req, resp);
        }
    }

    private boolean isAuthenticated(String username, String password){
        UserRepository userRepository = new UserRepository();
        userRepository.fillUsersList();
        boolean isAuthenticated = false;
        for (User user: userRepository.getUsersList()
        ) {
            if (user.getEmail().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                isAuthenticated = true;
                break;
            }
        }
        return isAuthenticated;
    }
}
