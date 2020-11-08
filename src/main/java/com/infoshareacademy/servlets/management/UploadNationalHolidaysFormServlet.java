package com.infoshareacademy.servlets.management;

import com.infoshareacademy.service.NationalHolidayService;

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

    @Inject NationalHolidayService nationalHolidayService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            performRequestWithValidation(req);
            view = getServletContext().getRequestDispatcher("/management.jsp");
        } else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void performRequestWithValidation(HttpServletRequest req) {
        String task = "";
        String message = "";
        boolean status = false;

        try {
            uploadNationalHolidaysFormHandler(req);
            message = "uploaded successfully";
            status = true;
        } catch (Exception e){
            message = "uploaded unsuccessfully";
        }

        req.getSession().setAttribute("task", "Day off");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }

    private void uploadNationalHolidaysFormHandler(HttpServletRequest req) throws Exception {
        String apiKeyInput = req.getParameter("apiKey");
        String selectedYear = req.getParameter("selectedYear");
        nationalHolidayService.executeApiTransferRequest(selectedYear, apiKeyInput);

    }
}