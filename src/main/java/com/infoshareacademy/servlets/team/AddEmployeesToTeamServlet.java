package com.infoshareacademy.servlets.team;

import com.infoshareacademy.model.Team;
import com.infoshareacademy.model.User;
import com.infoshareacademy.repository.TeamRepository;
import com.infoshareacademy.repository.UserRepository;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/addtoteam")
public class AddEmployeesToTeamServlet extends HttpServlet {

    @Inject
    private UserRepository userRepository;

    @Inject
    private TeamRepository teamRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            performRequestWithValidation(req);
            view = getServletContext().getRequestDispatcher("/team");

        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void performRequestWithValidation(HttpServletRequest req) {
        String task = "";
        String message = "";
        boolean status = false;

        try {
            String[] employeesChosenForATeam = req.getParameterValues("selectedUsersForTeam");
            addUsersToTeamFormHandler(req, employeesChosenForATeam);

            message = "added to team successfully";
            status = true;
        } catch (Exception e) {
            message = "added to team  unsuccessfully";
        }

        req.getSession().setAttribute("task", "Employees");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }

    public void addUsersToTeamFormHandler(HttpServletRequest req, String[] chosenEmployeesUsernames) {

        User loggedTeamLeader = userRepository.findByEmail(req.getSession().getAttribute("username").toString());
        List<String> updatedTeam = loggedTeamLeader.getTeam().getUserEmail();
        updatedTeam.addAll(Arrays.asList(chosenEmployeesUsernames));
        updatedTeam.stream().distinct().collect(Collectors.toList());

        loggedTeamLeader.getTeam().setUserEmail(updatedTeam);
        userRepository.update(loggedTeamLeader);

        List<User> usersInTeam = updatedTeam.stream()
                .map(employee -> userRepository.findById(Integer.parseInt(employee)))
                .filter(employee -> employee.getLevelOfAccess()==1)
                .collect(Collectors.toList());

        for(User user : usersInTeam){
            user.setTeam(loggedTeamLeader.getTeam());
            userRepository.update(user);
        }
    }
}
