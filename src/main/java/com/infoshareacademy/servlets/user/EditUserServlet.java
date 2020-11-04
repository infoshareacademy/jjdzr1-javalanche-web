package com.infoshareacademy.servlets.user;

import com.infoshareacademy.model.User;
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

@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {

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

            view = getServletContext().getRequestDispatcher("/employees");
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
            String password = req.getParameter("password");
            Integer userId = Integer.parseInt(req.getParameter("userId"));

            String name = req.getParameter("name");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            Integer levelOfAccess = Integer.valueOf(req.getParameter("levelOfAccess"));
            Integer daysOff = Integer.valueOf(req.getParameter("daysOff"));
            User user = userRepository.findByEmail(email);

            editUser(name, lastName, email, levelOfAccess, daysOff, user);

            message = "edited successfully";
            status = true;
        } catch (Exception e){
            message = "edited unsuccessfully";
        }

        req.getSession().setAttribute("task", "User");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }

    private void editUser(String name, String lastName, String email, Integer levelOfAccess, Integer daysOff, User user) {
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLevelOfAccess(levelOfAccess);
        user.setDaysOffLeft(daysOff);
        userRepository.update(user);
    }
}
