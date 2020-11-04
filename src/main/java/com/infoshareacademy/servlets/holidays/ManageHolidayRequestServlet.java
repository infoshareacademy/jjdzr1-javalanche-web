package com.infoshareacademy.servlets.holidays;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.UserRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@WebServlet("/manageholidays")
public class ManageHolidayRequestServlet extends HttpServlet {

    @Inject
    private DayOffRepository dayOffRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){
            performRequestWithValidation(req);

            view = getServletContext().getRequestDispatcher("/holidays");
            //resp.sendRedirect(req.getContextPath() + "/holidays");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void performRequestWithValidation(HttpServletRequest req) {
        String decision = "";
        String task = "";
        String message = "";
        boolean status = false;

        try {
            decision = manageHolidayRequestDecision(req);
            message = decision + " successfully";
            status = true;
        } catch (Exception e){
            message = decision + " unsuccessfully";
        }

        req.getSession().setAttribute("task", "Holiday request");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }

    private String manageHolidayRequestDecision(HttpServletRequest req) {
        if ("acceptholiday".equals(req.getQueryString())) {
            acceptHolidayRequest(req);
            return "accepted";
        }
        rejectHolidayRequest(req);
        return "rejected";
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
        int holidayRequestId = Integer.parseInt(req.getParameter("holidayId"));
        DayOff dayOff = dayOffRepository.findDaysOffByDayOffId(holidayRequestId);

        int amountOfDaysOffToReturn = new HashSet<>(dayOff.getListOfDays()).size();
        User user = dayOff.getUser();
        user.setDaysOffLeft(user.getDaysOffLeft() + amountOfDaysOffToReturn);
        userRepository.update(user);

        dayOff.setListOfDays(null);
        dayOff.setUser(null);
        dayOffRepository.update(dayOff);
        dayOffRepository.delete(dayOff);

    }


}
