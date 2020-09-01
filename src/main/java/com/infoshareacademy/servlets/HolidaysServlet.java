package com.infoshareacademy.servlets;

import com.infoshareacademy.TemplateProvider;
import com.infoshareacademy.api.Holidays;
import com.infoshareacademy.api.HolidaysJsonData;
import com.infoshareacademy.api.ServerResponse;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/testHolidayServlet")
public class HolidaysServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Template template = TemplateProvider.createTemplate(getServletContext(), "testHolidayServlet.ftlh");

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("holidays", holidaysList());


        try {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

       private List<Holidays> holidaysList(){
           return HolidaysJsonData.readDataFromJsonFile().getServerResponse().getHolidays();
    }
}
