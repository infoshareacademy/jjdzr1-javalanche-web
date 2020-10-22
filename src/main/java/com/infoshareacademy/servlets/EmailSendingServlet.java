package com.infoshareacademy.servlets;

import com.infoshareacademy.service.EmailService;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/emailsend")
public class EmailSendingServlet extends HttpServlet {

    private String host;
    private String port;
    private String userName;
    private String password;
    private static final Logger LOGGER = Logger.getLogger(EmailSendingServlet.class.getName());

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        host = servletContext.getInitParameter("host");
        port = servletContext.getInitParameter("port");
        userName = servletContext.getInitParameter("userName");
        password = servletContext.getInitParameter("password");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipient = req.getParameter("recipient");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");

        String resultMessage = "";

        try {
            EmailService.sendEmail(host, port, userName, password, recipient, subject, content);
            resultMessage = "The email was sent successfully";
        } catch (MessagingException e) {
            LOGGER.warning(e.getMessage());
            resultMessage = "There were an error " + e.getMessage();
        }
        finally {
            req.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
        }

    }
}
