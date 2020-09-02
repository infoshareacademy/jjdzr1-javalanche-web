<%@ page import="com.infoshareacademy.model.User" %>
<%@ page import="com.infoshareacademy.repository.UserRepository" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.infoshareacademy.repository.DayOffRepository" %>
<%@ page import="com.infoshareacademy.model.DayOff" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 29.08.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

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

</head>

<body>
<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Start Bootstrap</div>
        <div class="list-group list-group-flush">
            <a href="index.jsp" class="list-group-item list-group-item-action bg-light">Dashboard</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Shortcuts</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Overview</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Events</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Status</a>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="container-fluid" style="overflow: auto">
                <table class="table table-bordered table-sm m-1 p-1" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th scope="col" class="m-0 p-0"></th>
                        <%
                            List<LocalDate> dateList = new ArrayList<>();
                            for (int i = 0; i < LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()); i++) {
                                dateList.add(LocalDate.now().plusDays(i));
                                if (LocalDate.now().plusDays(i).getDayOfWeek().toString().equalsIgnoreCase("saturday") || LocalDate.now().plusDays(i).getDayOfWeek().toString().equalsIgnoreCase("sunday")) {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-warning rounded-0 m-0 p-0"
                                    style="height: 50px; width: 70px; font-size: xx-small"
                                    disabled>
                                <p style="margin-top: auto; margin-bottom: auto"><%= LocalDate.now().plusDays(i).getDayOfWeek()%>
                                </p>
                                <p style="margin-top: auto; margin-bottom: auto"><%= LocalDate.now().plusDays(i)%>
                                </p>
                            </button>
                        </th>
                        <%

                        } else {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-danger rounded-0 m-0 p-0"
                                    style="height: 50px; width: 70px; font-size: xx-small"
                                    disabled>
                                <p style="margin-top: auto; margin-bottom: auto"><%= LocalDate.now().plusDays(i).getDayOfWeek()%>
                                </p>
                                <p style="margin-top: auto; margin-bottom: auto"><%= LocalDate.now().plusDays(i)%>
                                </p>
                            </button>
                        </th>
                        <%
                            }
                        %>

                        <%
                            }
                        %>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        DayOffRepository dayOffRepository = new DayOffRepository();
                        dayOffRepository.fillDayOffList();
                        UserRepository userRepository = new UserRepository();
                        userRepository.fillUsersList();

                        Map<User, List<LocalDate>> userListMap = new HashMap<>();

                        for (User user : userRepository.getUsersList()) {
                            List<LocalDate> tempDayOffList = new ArrayList<>();
                            for (DayOff dayOff : dayOffRepository.getDayOffList()) {
                                if (user.getId() == dayOff.getIdOfUser()) {
                                    for (LocalDate date : dayOff.getListOfDays()) {
                                        tempDayOffList.add(date);
                                    }
                                }
                            }
                            userListMap.put(user, tempDayOffList);
                        }
                        for (User user : userRepository.getUsersList()) {
                    %>
                    <tr>
                        <th scope="col" class="m-0 p-0"
                            style="vertical-align: middle; text-align: end; font-size: x-small">
                            <button type="button" class="btn btn-outline-danger rounded-0 m-0 p-0"
                                    style="vertical-align: middle; text-align: end; font-size: small; width: 100px; height: 50px">
                                <p style="margin-top: auto; margin-bottom: auto"><%= user.getFirstName()%>
                                </p>
                                <p style="margin-top: auto; margin-bottom: auto"><%= user.getLastName()%>
                                </p>
                            </button>
                                <%
                                    for (LocalDate localDate: dateList) {
                                        if (userListMap.get(user).contains(localDate)) {

                                %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-success rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset">Day Off
                            </button>
                        </th>
                        <%

                        } else if (localDate.getDayOfWeek().toString().equalsIgnoreCase("saturday") || localDate.getDayOfWeek().toString().equalsIgnoreCase("sunday")) {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-secondary rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset"
                                    disabled><%= localDate%>
                            </button>
                        </th>
                        <% } else {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-secondary rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset"
                                    formmethod="post"
                                    input type="hidden" name="date" value="<%=localDate%>"><%= localDate%>
                            </button>
                        </th>
                        <%
                                }
                            }
                        %>
                        </th>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
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
</body>

</html>
