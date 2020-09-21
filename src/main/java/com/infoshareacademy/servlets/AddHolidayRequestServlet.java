package com.infoshareacademy.servlets;

import com.infoshareacademy.model.DayOff;
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
import java.util.logging.Logger;

@WebServlet("/addholidayrequest")
public class AddHolidayRequestServlet extends HttpServlet {

    @Inject
    private DayOffRepository dayOffRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private DayOffService dayOffService;

    private static final Logger logger = Logger.getLogger(AddHolidayRequestServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;

        if (req.getSession().getAttribute("username") != null){
            view = getServletContext().getRequestDispatcher("/main");
            LocalDate firstDay = LocalDate.parse(req.getParameter("firstDay"));
            LocalDate lastDay = LocalDate.parse(req.getParameter("lastDay"));

            DayOff dayOff = new DayOff();
            dayOff.setUser(userRepository.findByEmail((String) req.getSession().getAttribute("username")));
            dayOff.setFirstDay(firstDay);
            dayOff.setLastDay(lastDay);
            dayOff.setListOfDays(dayOffService.setListDaysWithoutWeekend(firstDay, lastDay));
            dayOffRepository.create(dayOff);

        } else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

}
