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
    <div class="container-fluid">
        <br>
        <h4>
            <i class="fas fa-search-plus"></i> Search for employee:
        </h4>
        <input class="form-control" id="myInput" type="text" placeholder="Type here..."><br>

        <table class="calendar" width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <th scope="col" class=""></th>
                <%
                    for (String date : calendarView) {
                        if (date.contains("MONDAY")
                                || date.contains("TUESDAY")
                                || date.contains("WEDNESDAY")
                                || date.contains("THURSDAY")
                                || date.contains("FRIDAY")) {
                %>
                <th>
                    <button type="button" class="button button-col-header-weekday" disabled><%=date.toUpperCase()%>
                    </button>
                </th>
                <% } else { %>
                <th>
                    <button type="button" class="button button-col-header-weekendday"
                            disabled><%=date.toUpperCase()%>
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
                <td>
                    <button type="button" class="button button-row-header-user"
                            data-toggle="modal"
                            data-target="#modalUser<%=i%>">

                        <%= users.get(i).getFirstName().toUpperCase()%><br>
                        <%= users.get(i).getLastName().toUpperCase()%>

                    </button>
                </td>
                <% for (String date : calendarView) { %>
                <% if (mapUsersAcceptedDaysOff.get(users.get(i).getEmail()).contains(date)) {
                %>
                <td>
                    <button type="button" class="button button-row-dayoff"
                            data-toggle="modal"
                            data-target="#modalDay" disabled>DAY OFF
                    </button>
                </td>
                <%
                    //FIXME
                } else if (mapUsersNotAcceptedDaysOff.get(users.get(i).getEmail()).contains(date)) {
                %>
                <td>
                    <button type="button" class="button button-row-pending"
                            data-toggle="modal"
                            data-target="#modalWithdrawHolidayRequest"
                            data-whatever="<%=date.substring(date.length()-10)%>"
                            <%if(!request.getSession().getAttribute("username").equals(users.get(i).getEmail())){%>disabled<%}%>>
                        PENDING
                    </button>
                </td>
                <%
                } else if (date.contains("MONDAY")
                        || date.contains("TUESDAY")
                        || date.contains("WEDNESDAY")
                        || date.contains("THURSDAY")
                        || date.contains("FRIDAY")) {
                %>
                <td>
                    <%request.setAttribute("currentDate", LocalDate.now().plusDays(i));%>
                    <button type="button" class="button button-row-weekday"
                            data-toggle="modal"
                            data-target="#modalPlaceHolidayRequest"
                            <%if(!request.getSession().getAttribute("username").equals(users.get(i).getEmail())){%>disabled<%}%>>
                    </button>
                </td>
                <%@include file="placeHolidayRequestDay.jsp" %>
                <%@include file="withdrawHolidayRequestDay.jsp" %>

                <% } else { %>
                <td>
                    <button type="button" class="button button-row-weekendday"
                            data-toggle="modal"
                            data-target="#modalDay" disabled><%/*date.toUpperCase()*/%>
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