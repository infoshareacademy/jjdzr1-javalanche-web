package com.infoshareacademy.servlets;

import com.infoshareacademy.DTO.UserDto;
import com.infoshareacademy.model.User;
import com.infoshareacademy.service.DayOffService;
import com.infoshareacademy.service.FormsService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    @Inject
    private TeamService teamService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);

        if(req.getQueryString().equals("addUser")){
            addUserFormHandler(req, resp);
        } else if (req.getQueryString().equals("deleteUser")){
            deleteUserFormHandler(req, resp);
        } else if (req.getQueryString().equals("placeHolidayRequest")){
            placeHolidayRequestFormHandler(req, resp);
        } else if (req.getQueryString().equals("withdrawHolidayRequest")){
            withdrawHolidayRequestFormHandler(req, resp);
        } else if (req.getQueryString().equals("addUsersToTeam")){
            addUsersToTeamFormHandler(req, resp);
        } else if (req.getQueryString().equals("addTeam")){
            addTeamFormHandler(req, resp);
        } else if (req.getQueryString().equals("deleteTeam")){
            deleteTeamFormHandler(req, resp);
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
            req.setAttribute("usersWithoutTeam", userService.createListOfEmployeesWithoutTeam());
            req.setAttribute("teamLeadersWithoutTeam", userService.createListOfTeamLeadersWithoutTeam());
            req.setAttribute("teamsList", teamService.getAll());
            req.setAttribute("loggedUser", userService.getByEmail(session.getAttribute("username").toString()));

        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void addUserFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("addUserEmail");
        String password = req.getParameter("addUserPassword");
        String firstName = req.getParameter("addUserFirstName");
        String surname = req.getParameter("addUserSurname");
        int daysOff = Integer.parseInt(req.getParameter("addUserDaysOff"));
        int levelOfAccess = Integer.parseInt(req.getParameter("levelOfAccess"));
        formsService.addUserFormInputDatabaseHandler(username, password, firstName, surname, daysOff, levelOfAccess);
    }

    private void deleteUserFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userIdToDelete = Integer.parseInt(req.getParameter("selectedIdToDelete"));
        formsService.deleteUserFormInputHandler(userIdToDelete);
    }

    private void placeHolidayRequestFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LocalDate holidayFirstDay = LocalDate.parse(req.getParameter("holidayFirstDay"));
        LocalDate holidayLastDay = LocalDate.parse(req.getParameter("holidayLastDay"));
        formsService.placeHolidayRequestInputHandler(holidayFirstDay, holidayLastDay, session.getAttribute("username").toString());
    }

    private void withdrawHolidayRequestFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int holidayRequestIdToDelete = Integer.parseInt(req.getParameter("selectedHolidayRequestToDelete"));
        formsService.deleteHolidayRequestFormInputHandler(holidayRequestIdToDelete);
    }

    private void addUsersToTeamFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeesChosenForATeam = req.getParameter("selectedUsersForTeam");
        logger.info(employeesChosenForATeam);
    }

    private void addTeamFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teamName = req.getParameter("addTeamName");
        String assignedTeamLeaderUsername = req.getParameter("assignTeamLeaderToGroup");
        formsService.addTeamFormInputHandler(teamName, assignedTeamLeaderUsername);
    }

    private void deleteTeamFormHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userIdToDelete = Integer.parseInt(req.getParameter("selectedTeamIdToDelete"));
        formsService.deleteTeamFormInputHandler(userIdToDelete);
    }

}
