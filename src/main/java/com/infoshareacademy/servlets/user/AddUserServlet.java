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

@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {

    @Inject
    private UserRepository userRepository;

    @Inject
    private SecurePasswordService securePasswordService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp);
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher view;
        if (req.getSession().getAttribute("username") != null){

            String name = req.getParameter("name");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            Integer levelOfAccess = Integer.valueOf(req.getParameter("levelOfAccess"));
            Integer daysOff = Integer.valueOf(req.getParameter("daysOff"));
            String password = req.getParameter("password");

            setNewUser(name, lastName, email, levelOfAccess, daysOff, password);

            view = getServletContext().getRequestDispatcher("/employees");

            resp.sendRedirect(req.getContextPath() + "/employees");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void setNewUser(String name, String lastName, String email, Integer levelOfAccess, Integer daysOff, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLevelOfAccess(levelOfAccess);
        user.setDaysOffLeft(daysOff);
        user.setPassword(securePasswordService.encryptor(password));
        userRepository.create(user);
    }
}
