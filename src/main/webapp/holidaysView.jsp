<%@ page import="com.infoshareacademy.DTO.DayOffDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<title>Holidays list</title>
<head>
    <%@ include file="features/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="template/sidebar.jsp" %>

    <div id="page-content-wrapper">
        <%@ include file="template/headerbar.jsp" %>

        <% UserDto user = (UserDto) request.getAttribute("user");%>
        <% List<DayOffDto> pendingHolidayRequests = (List<DayOffDto>) request.getAttribute("pendingHolidayRequests");%>

        <% if (pendingHolidayRequests.isEmpty()){ %>
        <%@include file="features/holidaysView/noHolidaysDisplay.jsp"%>
        <% } else { %>
        <%@include file="features/holidaysView/holidaysDisplay.jsp"%>
        <% } %>

    </div>

</div>
