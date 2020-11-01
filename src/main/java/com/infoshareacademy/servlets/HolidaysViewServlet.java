package com.infoshareacademy.servlets;

import com.infoshareacademy.DTO.DayOffDto;
import com.infoshareacademy.model.DayOff;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/holidays")
public class HolidaysViewServlet extends HttpServlet {

    @Inject
    private DayOffService dayOffService;

    @Inject
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            List<DayOffDto> pendingHolidayRequests = verifyIfListIsNotNull(dayOffService.getPendingHolidayRequests());
            req.setAttribute("pendingHolidayRequests", pendingHolidayRequests);
            req.setAttribute("user", userService.getByEmail(req.getSession().getAttribute("username").toString()));

            view = getServletContext().getRequestDispatcher("/holidaysView.jsp");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private List<DayOffDto> verifyIfListIsNotNull(List<DayOffDto> listToVerify){
        if(listToVerify==null){
            return new ArrayList<>();
        } else {
            return listToVerify;
        }
    }
}
