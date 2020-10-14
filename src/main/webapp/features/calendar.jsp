<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 16.09.2020
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">
    <h3 class="h3">Calendar</h3>
    <% List<String> calendarView = (List<String>) request.getAttribute("calendarView"); %>
    <% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>
    <% Map<String, List<LocalDate>> mapUsersDaysOff = (Map<String, List<LocalDate>>) request.getAttribute("map");%>
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
                    for (String date : calendarView) {
                        if (date.contains("MONDAY")
                                || date.contains("TUESDAY")
                                || date.contains("WEDNESDAY")
                                || date.contains("THURSDAY")
                                || date.contains("FRIDAY")) {
                %>
                <th scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-danger text-wrap rounded-0 m-0 p-0"
                            style="height: 50px; width: 70px; font-size: xx-small"><%=date%>
                    </button>
                </th>
                <% } else { %>
                <th scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-info text-wrap rounded-0 m-0 p-0"
                            style="height: 50px; width: 70px; font-size: xx-small"><%=date%>
                    </button>
                </th>
                <%
                        }
                    }
                %>
            </tr>
            </thead>
            <tbody id="calendarTable">
            <% for (UserDto user : users) { %>
            <tr>
                <td scope="col" class="m-0 p-0"
                    style="vertical-align: middle; text-align: end; font-size: x-small">
                    <button type="button" class="btn btn-outline-danger rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalUser"
                            style="vertical-align: middle; text-align: end; font-size: small; width: 100px; height: 50px">
                        <p style="margin-top: auto; margin-bottom: auto"><%= user.getFirstName()%>
                        </p>
                        <p style="margin-top: auto; margin-bottom: auto"><%= user.getLastName()%>
                        </p>
                    </button>
                </td>
                <% for (String date : calendarView) { %>
                <% if(mapUsersDaysOff.get(user.getEmail()).contains(date)) {
                %>
                <td scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-success rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset" disabled>Day off
                    </button>
                </td>
                <%
                } else if (date.contains("MONDAY")
                        || date.contains("TUESDAY")
                        || date.contains("WEDNESDAY")
                        || date.contains("THURSDAY")
                        || date.contains("FRIDAY")) {
                %>
                <td scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-secondary rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset"><%=date%>
                    </button>
                </td>

                <% } else { %>
                <td scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-info rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset" disabled><%=date%>
                    </button>
                </td>
                <%
                        }
                    } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
