package com.infoshareacademy.servlets.management;

import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.TeamService;
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

@WebServlet("/uploadDaysOff")
public class UploadDaysOffFormsServlet extends HttpServlet {

    @Inject
    private DayOffRepository dayOffRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
        resp.sendRedirect(req.getContextPath() + "/management");
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
            dayOffRepository.uploadDaysOffForNewYear();
            message = "uploaded successfully";
            status = true;
        } catch (Exception e){
            message = "uploaded unsuccessfully";
        }

        req.getSession().setAttribute("task", "Day off");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }


}
