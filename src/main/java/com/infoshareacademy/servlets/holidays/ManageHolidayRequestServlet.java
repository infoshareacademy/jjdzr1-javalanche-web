package com.infoshareacademy.servlets.holidays;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.service.SecurePasswordService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@WebServlet("/manageholidays")
public class ManageHolidayRequestServlet extends HttpServlet {

    @Inject
    private DayOffRepository dayOffRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){
            manageHolidayRequestDecision(req);
            view = getServletContext().getRequestDispatcher("/holidaysView.jsp");

            resp.sendRedirect(req.getContextPath() + "/holidays");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void manageHolidayRequestDecision(HttpServletRequest req) {
        switch (req.getQueryString()){
            case "acceptholiday" -> acceptHolidayRequest(req);
            case "declineholiday" -> rejectHolidayRequest(req);
        }
    }

    private void acceptHolidayRequest(HttpServletRequest req) {
        int holidayRequestId;
        DayOff dayOff;
        holidayRequestId = Integer.parseInt(req.getParameter("holidayId"));
        dayOff = dayOffRepository.findDaysOffByDayOffId(holidayRequestId);
        dayOff.setAccepted(true);
        dayOffRepository.update(dayOff);
    }

    private void rejectHolidayRequest(HttpServletRequest req) {
        //FIXME doesnt remove request
        int holidayRequestId = Integer.parseInt(req.getParameter("holidayId"));
        DayOff dayOff = dayOffRepository.findDaysOffByDayOffId(holidayRequestId);
        dayOff.setListOfDays(null);
        dayOff.setUser(null);
        dayOffRepository.update(dayOff);
        dayOffRepository.delete(dayOff);
    }


}
