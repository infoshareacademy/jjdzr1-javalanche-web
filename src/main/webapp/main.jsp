<%@ page import="com.infoshareacademy.model.User" %>
<%@ page import="com.infoshareacademy.repository.UserRepository" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.infoshareacademy.repository.DayOffRepository" %>
<%@ page import="com.infoshareacademy.model.DayOff" %>
<%@ page import="com.infoshareacademy.api.HolidaysJsonData" %>
<%@ page import="com.infoshareacademy.api.Holidays" %>
<%@ page import="java.util.*" %>
<%@ page import="com.infoshareacademy.service.DayOffService" %>
<%@ page import="com.infoshareacademy.service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 29.08.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<title>Holiday calendar</title>
<head>
    <%@ include file="features/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="template/sidebar.jsp" %>

    <div id="page-content-wrapper" >
        <%@ include file="template/headerbar.jsp" %>
        <%@ include file="features/calendar.jsp" %>
        <%@ include file="features/popup.jsp" %>



        <%--INSERT YOUR CODE HERE--%>
        <%--INSERT YOUR CODE HERE--%>
        <%--INSERT YOUR CODE HERE--%>

    </div>

</div>

</body>
</html>