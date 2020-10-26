package com.infoshareacademy.servlets.settings;

import com.infoshareacademy.service.FormsService;
import com.infoshareacademy.service.UserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changePasswordForm")
public class EditPasswordServlet extends HttpServlet {

    @Inject
    private FormsService formsService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        changePasswordFormHandler(req, resp);
        resp.sendRedirect(req.getContextPath() + "/forms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/settings.jsp");
            setAttributes(req, session);
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }


    private void setAttributes(HttpServletRequest req, HttpSession session) {

    }

    private void changePasswordFormHandler(HttpServletRequest req, HttpServletResponse resp) {
        if (formsService.verifyIfPasswordsMatch(req.getParameter("accountOldPassword"), userService.getByEmail(req.getSession().getAttribute("username").toString()).getPassword()) && formsService.verifyIfPasswordsMatch(req.getParameter("accountNewPassword"), req.getParameter("accountNewPasswordRepeated"))) {
            formsService.changePasswordInputHandler(
                    req.getSession().getAttribute("username").toString(),
                    req.getParameter("accountNewPassword"));
        }
    }
}
