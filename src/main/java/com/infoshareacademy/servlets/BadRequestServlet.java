package com.infoshareacademy.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/badrequest_404")
public class BadRequestServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BadRequestServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = getServletContext().getRequestDispatcher("/404.jsp");
        view.forward(req, resp);
    }
}
