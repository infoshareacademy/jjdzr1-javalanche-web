package com.infoshareacademy.servlets.settings;

import com.infoshareacademy.service.UserService;
import com.infoshareacademy.servlets.TestServlet;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(TestServlet.class.getName());

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){
            view = getServletContext().getRequestDispatcher("/settings.jsp");
        } else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, HttpSession session){
        req.getSession().getAttribute("levelOfAccess");
        req.setAttribute("loggedUser", userService.getByEmail(session.getAttribute("username").toString()));
    }
}
