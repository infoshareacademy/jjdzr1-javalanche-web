package com.infoshareacademy.servlets;

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

        Template template = TemplateProvider.createTemplate(getServletContext(), "login.ftlh");

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("zmienna", "Moja Zmianne");

        try {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("jan@kowalski.pl") && password.equals("1234")) {
            RequestDispatcher view = getServletContext().getRequestDispatcher("/main");
            view.forward(req, resp);

        } else {
            Template template = TemplateProvider.createTemplate(getServletContext(), "login.ftlh");
            Map<String, Object> dataModel = new HashMap<>();

            try {
                template.process(dataModel, writer);
            } catch (TemplateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
