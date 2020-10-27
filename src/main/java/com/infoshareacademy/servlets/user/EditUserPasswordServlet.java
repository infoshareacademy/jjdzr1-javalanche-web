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

@WebServlet("/editpassword")
public class EditUserPasswordServlet extends HttpServlet {

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

            String password = req.getParameter("password");
            Integer changePasswordUserId = Integer.parseInt(req.getParameter("changePasswordUserId"));

            editUser(password, changePasswordUserId);

            view = getServletContext().getRequestDispatcher("/employeesView.jsp");

            resp.sendRedirect(req.getContextPath() + "/employees");
        }
        else {
            view = getServletContext().getRequestDispatcher("/badrequest_404");
        }
        view.forward(req, resp);
    }

    private void editUser(String password, Integer changePasswordUserId) {
        User user = userRepository.findById(changePasswordUserId);
        user.setPassword(securePasswordService.encryptor(password));
        userRepository.update(user);
    }
}
