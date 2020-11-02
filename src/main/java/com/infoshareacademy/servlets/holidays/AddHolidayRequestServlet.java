package com.infoshareacademy.servlets.holidays;

import com.infoshareacademy.service.HolidayService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addHolidayRequest")
public class AddHolidayRequestServlet extends HttpServlet {

    @Inject
    private HolidayService holidayService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null) {

            LocalDate startDay = LocalDate.parse(req.getParameter("StartDate"));
            LocalDate endDay = LocalDate.parse(req.getParameter("EndDate"));
            holidayService.setNewHolidayRequest(startDay, endDay, req);

            view = getServletContext().getRequestDispatcher("/main.jsp");

            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

}
