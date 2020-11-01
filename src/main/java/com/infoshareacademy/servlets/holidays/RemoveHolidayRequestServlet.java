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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/withdrawHolidayRequest")
public class RemoveHolidayRequestServlet extends HttpServlet {

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

            withdrawHoliday(req);
            view = getServletContext().getRequestDispatcher("/main.jsp");

            resp.sendRedirect(req.getContextPath() + "/main");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void withdrawHoliday(HttpServletRequest req) {
        User user = userRepository.findByEmail(req.getSession().getAttribute("username").toString());

        LocalDate returnedDay = LocalDate.parse(req.getParameter("chosenDay"));

        List<DayOff> foundHoliday = dayOffRepository
                .findDaysOffIdByDayOff(returnedDay)
                .stream()
                .filter(dayOff -> dayOff.getUser().getId()==user.getId())
                .collect(Collectors.toList());
        DayOff dayOff = foundHoliday.get(0);

        int amountOfDaysOffToReturn = dayOff.getListOfDays().size();
        user.setDaysOffLeft(user.getDaysOffLeft() + amountOfDaysOffToReturn);
        userRepository.update(user);

        dayOff.setUser(null);
        dayOff.setListOfDays(null);
        dayOffRepository.update(dayOff);
        dayOffRepository.delete(dayOff);

    }

}
