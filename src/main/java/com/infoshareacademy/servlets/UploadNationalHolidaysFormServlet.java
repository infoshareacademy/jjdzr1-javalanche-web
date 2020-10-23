package com.infoshareacademy.servlets;

import com.infoshareacademy.model.User;
import com.infoshareacademy.service.FormsService;
import com.infoshareacademy.service.NationalHolidayService;
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

@WebServlet("/uploadNationalHolidaysForm")
public class UploadNationalHolidaysFormServlet extends HttpServlet {

    @Inject
    private NationalHolidayService nationalHolidayService;
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uploadNationalHolidaysFormHandler(req);
        resp.sendRedirect(req.getContextPath() + "/managementForms");
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/managementForms.jsp");
            setAttributes(req);
        } else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req) {
        req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));
        req.setAttribute("users", userService.getAll());
    }

    private void uploadNationalHolidaysFormHandler(HttpServletRequest req){
        String apiKeyInput = req.getParameter("apiKey");
        String selectedYear = req.getParameter("selectedYear");
        boolean submissionStatus;
        submissionStatus = nationalHolidayService.executeApiTransferRequest(selectedYear, apiKeyInput);
        if(submissionStatus){
            req.getSession().setAttribute("managementModificationStatus", "National holidays uploaded to database successfully.");
        } else {
            req.getSession().setAttribute("managementModificationStatus", "System error. Uploading national holidays to database was unsuccessful.");
        }
    }
}