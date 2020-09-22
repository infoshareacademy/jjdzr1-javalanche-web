package com.infoshareacademy.servlets;

import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.FormsService;
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
import java.time.LocalDate;
import java.util.logging.Logger;

@WebServlet("/forms")
public class FormsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FormsServlet.class.getName());

    @Inject
    private FormsService formsService;

    @Inject
    private UserService userService;

    @Inject
    private DayOffService dayOffService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
        HttpSession session = req.getSession();

        if(req.getQueryString().equals("addUser")){
            String username = req.getParameter("addUserEmail");
            String password = req.getParameter("addUserPassword");
            String firstName = req.getParameter("addUserFirstName");
            String surname = req.getParameter("addUserSurname");
            int daysOff = Integer.parseInt(req.getParameter("addUserDaysOff"));
            int levelOfAccess = Integer.parseInt(req.getParameter("levelOfAccess"));
            formsService.addUserFormInputDatabaseHandler(username, password, firstName, surname, daysOff, levelOfAccess);
        } else if (req.getQueryString().equals("deleteUser")){
            int userIdToDelete = Integer.parseInt(req.getParameter("selectedIdToDelete"));
            formsService.deleteUserFormInputHandler(userIdToDelete);
        } else if (req.getQueryString().equals("placeHolidayRequest")){
            LocalDate holidayFirstDay = LocalDate.parse(req.getParameter("holidayFirstDay"));
            LocalDate holidayLastDay = LocalDate.parse(req.getParameter("holidayLastDay"));
            formsService.placeHolidayRequestInputHandler(holidayFirstDay, holidayLastDay, session.getAttribute("username").toString());
        } else if (req.getQueryString().equals("withdrawHolidayRequest")){
            int holidayRequestIdToDelete = Integer.parseInt(req.getParameter("selectedHolidayRequestToDelete"));
            formsService.deleteHolidayRequestFormInputHandler(holidayRequestIdToDelete);
        }
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/forms.jsp");
            req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));
            req.setAttribute("users", userService.getAll());
            req.setAttribute("daysOffRequests", dayOffService.getByUserEmail(session.getAttribute("username").toString()));
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }
}
