package com.infoshareacademy.servlets;
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
import java.util.logging.Logger;

@WebServlet("/forms")
public class FormsServlet extends HttpServlet {

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

        switch (req.getQueryString()) {
            case "addUser" -> addUserFormHandler(req);
            case "deleteUser" -> deleteUserFormHandler(req);
            case "placeHolidayRequest" -> placeHolidayRequestFormHandler(req);
            case "withdrawHolidayRequest" -> withdrawHolidayRequestFormHandler(req);
            case "holidayRequestDecision" -> holidayRequestDecisionFormHandler(req);
            case "addUsersToTeam" -> addUsersToTeamFormHandler(req);
            case "removeUsersFromTeam" -> removeUsersFromTeamFormHandler(req);
            case "addTeam" -> addTeamFormHandler(req);
            case "deleteTeam" -> deleteTeamFormHandler(req);
        }
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            view = getServletContext().getRequestDispatcher("/forms.jsp");
            setAttributes(req, session);
        }
        else {
            view = getServletContext().getRequestDispatcher("/404.html");
        }
        view.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, HttpSession session){
        req.setAttribute("levelOfAccess", req.getSession().getAttribute("levelOfAccess"));
        req.setAttribute("users", userService.getAll());
        req.setAttribute("daysOffRequests", dayOffService.pendingHolidayRequests(session.getAttribute("username").toString()));
        req.setAttribute("usersWithoutTeam", userService.createListOfEmployeesWithoutTeam());
        req.setAttribute("employeesInTeam", userService.createListOfEmployeesInThisTeam(session.getAttribute("username").toString()));
        req.setAttribute("teamLeadersWithoutTeam", userService.createListOfTeamLeadersWithoutTeam());
        req.setAttribute("teamsList", teamService.getAll());
        req.setAttribute("loggedUser", userService.getByEmail(session.getAttribute("username").toString()));
        req.setAttribute("holidayRequests", dayOffService.getAll());
    }

    private void addUserFormHandler(HttpServletRequest req) {
        User userToAdd = new User();
        userToAdd.setEmail(req.getParameter("addUserEmail"));
        userToAdd.setPassword(req.getParameter("addUserPassword"));
        userToAdd.setFirstName(req.getParameter("addUserFirstName"));
        userToAdd.setLastName(req.getParameter("addUserSurname"));
        userToAdd.setDaysOffLeft(Integer.parseInt(req.getParameter("addUserDaysOff")));
        userToAdd.setLevelOfAccess(Integer.parseInt(req.getParameter("levelOfAccess")));
        formsService.addUserFormInputDatabaseHandler(userToAdd);
    }

    private void deleteUserFormHandler(HttpServletRequest req) {
        int userIdToDelete = Integer.parseInt(req.getParameter("selectedIdToDelete"));
        formsService.deleteUserFormInputHandler(userIdToDelete);
    }

    private void placeHolidayRequestFormHandler(HttpServletRequest req) {
        HttpSession session = req.getSession();
        LocalDate holidayFirstDay = LocalDate.parse(req.getParameter("holidayFirstDay"));
        LocalDate holidayLastDay = LocalDate.parse(req.getParameter("holidayLastDay"));
        formsService.placeHolidayRequestInputHandler(holidayFirstDay, holidayLastDay, session.getAttribute("username").toString());
    }

    private void withdrawHolidayRequestFormHandler(HttpServletRequest req) {
        int holidayRequestIdToDelete = Integer.parseInt(req.getParameter("selectedHolidayRequestToDelete"));
        formsService.deleteHolidayRequestFormInputHandler(holidayRequestIdToDelete);
    }

    private void holidayRequestDecisionFormHandler(HttpServletRequest req) {
        int chosenHolidayRequestId = Integer.parseInt(req.getParameter("selectedHolidayRequest"));
        Boolean isRequestAccepted = Boolean.parseBoolean(req.getParameter("holidayRequestVerdict"));
        formsService.holidayRequestDecisionFormInputHandler(chosenHolidayRequestId, isRequestAccepted);
    }
    
    private void addUsersToTeamFormHandler(HttpServletRequest req) {
        String loggedTeamLeader = req.getSession().getAttribute("username").toString();
        String[] employeesChosenForATeam = req.getParameterValues("selectedUsersForTeam");
        formsService.addUsersToTeamFormInputHandler(loggedTeamLeader, employeesChosenForATeam);
    }

    private void removeUsersFromTeamFormHandler(HttpServletRequest req) {
        String loggedTeamLeader = req.getSession().getAttribute("username").toString();
        String[] employeesChosenForRemovalFromTeam = req.getParameterValues("selectedUsersToRemoveFromTeam");
        formsService.removeUsersFromTeamInputHandler(loggedTeamLeader, employeesChosenForRemovalFromTeam);
    }

    private void addTeamFormHandler(HttpServletRequest req) {
        String teamName = req.getParameter("addTeamName");
        String assignedTeamLeaderUsername = req.getParameter("assignTeamLeaderToGroup");
        formsService.addTeamFormInputHandler(teamName, assignedTeamLeaderUsername);
    }

    private void deleteTeamFormHandler(HttpServletRequest req) {
        int teamIdToDelete = Integer.parseInt(req.getParameter("selectedTeamIdToDelete"));
        formsService.deleteTeamFormInputHandler(teamIdToDelete);
    }

}
