package com.infoshareacademy.servlets;

import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.service.ValidationService;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Inject
    private ValidationService validationService;

    @Inject
    private UserRepository userRepository;

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

        if (validationService.isAuthenticated(username, password)) {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/main");

            session = req.getSession();
            // przekazywanie w sesji nazwy zalogowanego użytkownika
            session.setAttribute("username", username);
            // przekazywanie w sesji pozimou dostępu
            session.setAttribute("levelOfAccess", userRepository.findByEmail(username).getLevelOfAccess() );

            view.forward(req, resp);

        } else {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp");
            view.forward(req, resp);
        }
    }
}
