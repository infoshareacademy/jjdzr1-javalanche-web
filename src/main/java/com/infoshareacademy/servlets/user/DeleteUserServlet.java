package com.infoshareacademy.servlets.user;

import com.infoshareacademy.repository.UserRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    @Inject
    private UserRepository userRepository;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        performRequestWithValidation(req);

        RequestDispatcher view = getServletContext().getRequestDispatcher("/employees");

        view.forward(req, resp);
    }

    private void performRequestWithValidation(HttpServletRequest req) {
        String task = "";
        String message = "";
        boolean status = false;

        try {
            Integer userId = Integer.valueOf(req.getParameter("userId"));
            userRepository.delete(userRepository.findById(userId));

            message = "deleted successfully";
            status = true;
        } catch (Exception e){
            message = "deleted unsuccessfully";
        }

        req.getSession().setAttribute("task", "User");
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("success", status);
    }
}
