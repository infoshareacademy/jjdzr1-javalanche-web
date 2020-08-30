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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

        logger.log(Level.SEVERE, holidaysList().toString());

        dataModel.put("holidays", holidaysList());

        holidaysList().forEach(System.out::println);

        try{
            ServletContext context = getServletContext();
            InputStream inp = context.getResourceAsStream("db_holidaysNational.json");
            if (inp != null) {
        }
        }
        catch(Exception e){

        }

        try {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

       private List<Holidays> holidaysList(){
           HolidaysJsonData holidaysJsonData = HolidaysJsonData.readDataFromJsonFile();
           ServerResponse serverResponse = holidaysJsonData.getServerResponse();
           List<Holidays> holidays = serverResponse.getHolidays();
           return holidays;
    }
}
