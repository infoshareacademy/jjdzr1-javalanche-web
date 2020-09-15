<%@ page import="com.infoshareacademy.model.User" %>
<%@ page import="com.infoshareacademy.repository.UserRepository" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.infoshareacademy.repository.DayOffRepository" %>
<%@ page import="com.infoshareacademy.model.DayOff" %>
<%@ page import="com.infoshareacademy.api.HolidaysJsonData" %>
<%@ page import="com.infoshareacademy.api.Holidays" %>
<%@ page import="java.util.*" %>
<%@ page import="com.infoshareacademy.service.DayOffDaoService" %>
<%@ page import="com.infoshareacademy.service.UserDaoService" %><%--
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


</head>

<body>
<div class="d-flex" id="wrapper">

    <%@ include file="sidebar.jsp" %>

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <%@include file="navbar.jsp"%>

        <div class="container-fluid">
            <div class="container-fluid" style="overflow: auto">
                <br>
                <h3>
                    Search for employee:
                </h3>
                <input class="form-control" id="myInput" type="text" placeholder="Type here..."><br>

                <table class="table table-bordered table-sm m-1 p-1" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th scope="col" class="m-0 p-0"></th>
                        <%
                            List<LocalDate> dateList = new ArrayList<>();

                            for (int i = 0; i < LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()); i++) {
                                dateList.add(LocalDate.now().plusDays(i));

                                boolean isNationalHoliday = false;
                                String message = "";

                                for (Holidays nationalHoliday : HolidaysJsonData.returnOnlyHolidaysAsList()) {
                                    if (nationalHoliday.getHolidayDateInLocalDateFormat().equals(LocalDate.now().plusDays(i))) {
                                        isNationalHoliday = true;
                                        message = nationalHoliday.getName();
                                    }
                                }

                                if (LocalDate.now().plusDays(i).getDayOfWeek().toString().equalsIgnoreCase("saturday")
                                        || LocalDate.now().plusDays(i).getDayOfWeek().toString().equalsIgnoreCase("sunday")
                                        || isNationalHoliday) {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-warning rounded-0 m-0 p-0"
                                    style="height: 50px; width: 70px; font-size: xx-small"
                                    disabled>
                                <p style="margin-top: auto; margin-bottom: auto"><%= message.toUpperCase()%>
                                </p>
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
                    <%
                        DayOffDaoService dayOffDaoService = (DayOffDaoService) request.getAttribute("dayOffDaoService");
                        UserDaoService userDaoService = (UserDaoService) request.getAttribute("userDaoService");
                    %>
                    <tbody id="calendarTable">
                    <%
                        for (User u : userDaoService.getAll()) {
                            List<LocalDate> listOfDaysOff = new ArrayList<>();
                            dayOffDaoService.getDaysOffByUserId(u.getId()).forEach(dayOff -> {
                                dayOff.getListOfDays().forEach(localDate -> {
                                    listOfDaysOff.add(localDate);
                                });
                            });
                    %>
                    <!-- Pęta do wyświetlania użytkowników -->
                    <tr>
                        <!-- Wyświetlanie kolumny z użytkownikami -->
                        <td scope="col" class="m-0 p-0"
                            style="vertical-align: middle; text-align: end; font-size: x-small">
                            <button type="button" class="btn btn-outline-danger rounded-0 m-0 p-0" data-toggle="modal"
                                    data-target=#
                                    style="vertical-align: middle; text-align: end; font-size: small; width: 100px; height: 50px">
                                <p style="margin-top: auto; margin-bottom: auto"><%= u.getFirstName()%>
                                </p>
                                <p style="margin-top: auto; margin-bottom: auto"><%= u.getLastName()%>
                                </p>
                            </button>
                        </td>
                        <!--// Wyświetlanie kolumny z użytkownikami -->
                        <!-- Wyświetlanie wierszy kalendarza dla każdego użytkownika -->
                        <%
                            for (int i = 0; i < 30; i++) {
                                if (LocalDate.now().plusDays(i).getDayOfWeek().toString().equalsIgnoreCase("saturday")
                                        || LocalDate.now().plusDays(i).getDayOfWeek().toString().equalsIgnoreCase("sunday")) {
                        %>
                        <td scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-secondary rounded-0 m-0 p-0" data-toggle="modal"
                                    data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset"
                                    disabled><%=LocalDate.now().plusDays(i)%>

                            </button>
                        </td>
                        <%
                        }
                                else if (listOfDaysOff.contains(LocalDate.now().plusDays(i))) {
                        %>
                        <td scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-success rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset">Day off
                            </button>
                        </td>
                        <%
                        }
                                else {
                        %>
                        <td scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-secondary rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset"><%= LocalDate.now().plusDays(i)%>
                            </button>
                        </td>
                        <%
                                }
                            }
                        %>
                        <!--// Wyświetlanie wierszy kalendarza dla każdego użytkownika-->
                    </tr>

                    <%
                        }
                    %>
                    <!--// Pęta do wyświetlania użytkowników -->
                    </tbody>
                </table>
            </div>
        </div>
        <!-- testowa czesc strony -->
        <div>
            <% for (Cookie c: request.getCookies()) {
            %>
                <br><%= c.getName()%>
                <br><%= c.getValue()%>
                <br><% if (c.getName().equalsIgnoreCase("id"))%><%=userDaoService.getUserById(Integer.parseInt(c.getValue()))%>
            <%
                }
            %>

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