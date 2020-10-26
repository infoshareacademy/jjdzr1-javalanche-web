<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Map" %>

<div class="container-fluid">
    <h3 class="h3" style="margin-top: 20px">
        <i class="far fa-calendar-alt"></i> Calendar
    </h3>

    <% List<String> calendarView = (List<String>) request.getAttribute("calendarView"); %>
    <% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>
    <% Map<String, List<LocalDate>> mapUsersAcceptedDaysOff = (Map<String, List<LocalDate>>) request.getAttribute("acceptedHolidays");%>
    <% Map<String, List<LocalDate>> mapUsersNotAcceptedDaysOff = (Map<String, List<LocalDate>>) request.getAttribute("notAcceptedHolidays");%>
    <div class="container-fluid" style="overflow: auto">
        <br>
        <h4>
            <i class="fas fa-search-plus"></i> Search for employee:
        </h4>
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
                            style="height: 50px; width: 70px; font-size: xx-small"><%=date.toUpperCase()%>
                    </button>
                </th>
                <%
                        }
                    }
                %>
            </tr>
            </thead>
            <tbody id="calendarTable">
            <% for (int i = 0; i < users.size(); i++) { %>
            <tr>
                <td scope="col" class="m-0 p-0"
                    style="vertical-align: middle; text-align: end; font-size: x-small">
                    <button type="button" class="btn btn-outline-secondary rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalUser<%=i%>"
                            style="vertical-align: middle; text-align: center; font-size: small; width: 100px; height: 50px; font-weight: bold;">
                        <p style="margin-top: auto; margin-bottom: auto"><%= users.get(i).getFirstName()%>
                        </p>
                        <p style="margin-top: auto; margin-bottom: auto"><%= users.get(i).getLastName()%>
                        </p>
                    </button>
                </td>
                <% for (String date : calendarView) { %>
                <% if (mapUsersAcceptedDaysOff.get(users.get(i).getEmail()).contains(date)) {
                %>
                <td scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-success rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset" disabled>Day off
                    </button>
                </td>
                <% } else if (mapUsersNotAcceptedDaysOff.get(users.get(i).getEmail()).contains(date)) {
                %>
                <td scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-warning rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset" disabled>Pending
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
                    <%request.setAttribute("currentDate", LocalDate.now().plusDays(i));%>
                    <button type="button" class="btn btn-secondary rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            data-date="@<%=date%>"
                            data-userId="@<%=users.get(i).getId()%>"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset"
                            <%if(!request.getSession().getAttribute("username").equals(users.get(i).getEmail())){%>disabled<%}%>><%=date%>
                    </button>
                </td>
                <%@include file="popupDay.jsp"%>

                <% } else { %>
                <td scope="col" class="m-0 p-0">
                    <button type="button" class="btn btn-info rounded-0 m-0 p-0 text-wrap"
                            data-toggle="modal"
                            data-target="#modalDay"
                            style="width: 70px; height: 50px; font-size: xx-small; padding: unset"
                            disabled><%=date.toUpperCase()%>
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
