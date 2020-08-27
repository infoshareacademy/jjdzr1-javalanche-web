package com.infoshareacademy.servlets;

import com.infoshareacademy.TemplateProvider;
import com.infoshareacademy.repository.DayOffRepository;
import com.infoshareacademy.repository.UserRepository;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/CalendarView")
public class CalendarViewServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        Template template = TemplateProvider.createTemplate(getServletContext(), "calendarview.ftlh");

        Map<String, Object> dataModel = new HashMap<>();

        /* TODO
            Tworzymy listę dat do nagłówka
            W późniejszym etapie metoda do przesunięcia w odrębną klasę
         */
        LocalDate date = LocalDate.now();
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(date);
        for (int i = 1; i < LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()); i++) {
            dateList.add(dateList.get(i-1).plusDays(1));
        }
        dataModel.put("dates", dateList);

        /* TODO
            Tworzymy listę użytkowników aby dodać do wierszy
            W późniejszym etapie metoda do usunięcia
         */
        UserRepository userRepository = new UserRepository();
        userRepository.fillUsersList();
        dataModel.put("users", userRepository.getUsersList());

        /* TODO
            Tworzymy listę dni wolnych aby wyświetlać w tabeli
            W późniejszym etapie metoda do usunięcia
         */

        DayOffRepository dayOffRepository = new DayOffRepository();
        dayOffRepository.fillDayOffList();
        dataModel.put("daysoff", dayOffRepository.getDayOffList());

        try {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        Template template = TemplateProvider.createTemplate(getServletContext(), "calendarview.ftlh");

        Map<String, Object> dataModel = new HashMap<>();

        try {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
