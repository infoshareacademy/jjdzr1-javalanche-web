<%@ page import="com.infoshareacademy.model.User" %>
<%@ page import="com.infoshareacademy.repository.UserRepository" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.infoshareacademy.repository.DayOffRepository" %>
<%@ page import="com.infoshareacademy.model.DayOff" %>
<%@ page import="com.infoshareacademy.api.HolidaysJsonData" %>
<%@ page import="com.infoshareacademy.api.Holidays" %>
<%@ page import="java.util.*" %>
<%@ page import="com.infoshareacademy.service.DayOffService" %>
<%@ page import="com.infoshareacademy.service.UserService" %>
<%@ page import="com.infoshareacademy.DTO.TeamDto" %>
<%@ page import="com.infoshareacademy.DTO.UserDto" %><%--
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"></link>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <!--    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->

    <!-- Custom styles for this template -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

    <!-- Custom styles for table   -->
    <link href="css/table_date.css" rel="stylesheet">
    <meta http-equiv="refresh">

</head>

<body>
<div class="d-flex" id="wrapper">

    <%@ include file="sidebar.jsp" %>

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <%@include file="navbar.jsp"%>
<%--        <%@include file="calendar.jsp"%>--%>
        <!-- testowa czesc strony -->
        <div>

<%--            <div class="container-fluid">--%>
<%--                <% int levelOfAccess = (int) session.getAttribute("levelOfAccess");%>--%>
<%--                <% if (levelOfAccess == 1){ %>--%>
<%--                <button type="button" class="btn-info visible">Button test</button>--%>
<%--                <% } else { %>--%>
<%--                <button type="button" class="btn-info invisible">Button test</button>--%>
<%--                <% } %>--%>

<%--            </div>--%>

        </div>
        <br><br>
        <h3> testing: </h3>
        <div>
            <% List<TeamDto> teams = (List<TeamDto>) request.getAttribute("teams");%>
            <% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>
            <% for (TeamDto t: teams) { %>
                <p><%=t%></p>
                <br><br>
            <% } %>
            <% for (UserDto u: users) { %>
                <p><%=u%></p>
                <br><br>
            <% } %>
        </div>
        <!-- // testowa czesc strony -->

    </div>


    <!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->
<!-- /#MODAL -->
<div class="modal fade modalDay" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <p>
                POPAPEK
            </p>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#calendarTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>

</html>
