<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@include file="template/header.jsp" %>

<!-- MAIN CONTENT GOES HERE -->

<% UserDto teamLeader = (UserDto) request.getAttribute("teamLeader"); %>
<% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>

<% if (teamLeader.getTeam() == null || teamLeader.getTeam().getUserEmail().isEmpty()) { %>
<%@include file="features/teamView/noTeamLeaderDisplay.jsp" %>
<% } else { %>
<%@include file="features/teamView/teamDisplay.jsp" %>
<% } %>
<!-- END OF MAIN CONTENT -->
<%@include file="template/footer.jsp" %>

