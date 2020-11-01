package com.infoshareacademy.servlets.holidays;

import com.infoshareacademy.model.DayOff;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.service.DayOffService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/addHolidayRequest")
public class AddHolidayRequestServlet extends HttpServlet {

    @Inject
    private DayOffRepository dayOffRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private DayOffService dayOffService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            LocalDate startDay = LocalDate.parse(req.getParameter("StartDate"));
            LocalDate endDay = LocalDate.parse(req.getParameter("EndDate"));
            setNewHolidayRequest(startDay, endDay, req);

            view = getServletContext().getRequestDispatcher("/main.jsp");

            resp.sendRedirect(req.getContextPath() + "/main");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void setNewHolidayRequest(LocalDate startDay, LocalDate endDay, HttpServletRequest req) {
        User user = userRepository.findByEmail(req.getSession().getAttribute("username").toString());
        DayOff dayOff = new DayOff();
        dayOff.setFirstDay(startDay);
        dayOff.setLastDay(endDay);
        dayOff.setUser(user);
        dayOff.setAccepted(user.getLevelOfAccess() == 3);
        dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(startDay, endDay));
        dayOffRepository.create(dayOff);
        user.setDaysOffLeft((int) (user.getDaysOffLeft() - ChronoUnit.DAYS.between(startDay, endDay) - 1));
        userRepository.update(user);
    }

}
