<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.infoshareacademy.model.Team" %>
<%@ page import="com.infoshareacademy.DTO.TeamDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<title>Holiday calendar</title>
<head>
    <%@ include file="template/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="template/sidebar.jsp" %>

    <div id="page-content-wrapper" >
        <%@ include file="template/headerbar.jsp" %>

        <% List<TeamDto> teams = (List<TeamDto>) request.getAttribute("teams");%>
        <% UserDto admin = (UserDto) request.getAttribute("admin");%>
        <% List<UserDto> teamLeaders = (List<UserDto>) request.getAttribute("teamLeaders");%>
        <% List<UserDto> usersInTeam = (List<UserDto>) request.getAttribute("userWithTeam");%>

        <% if (teams.isEmpty()){ %>
        <%@include file="features/teamsView/noTeamsDisplay.jsp"%>
        <% } else { %>
        <%@include file="features/teamsView/teamsDisplay.jsp"%>
        <% } %>
    </div>

</div>
