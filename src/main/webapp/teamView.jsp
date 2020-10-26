<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
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

        <% UserDto teamLeader = (UserDto) request.getAttribute("teamLeader"); %>
        <% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>

        <% if (teamLeader.getTeam() == null || teamLeader.getTeam().getUserEmail().isEmpty()){ %>
        <%@include file="features/teamView/noTeamLeaderDisplay.jsp"%>
        <% } else { %>
        <%@include file="features/teamView/teamDisplay.jsp"%>
        <% } %>
    </div>

</div>
