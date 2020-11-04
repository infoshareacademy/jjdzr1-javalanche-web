<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Map" %>
<head>
    <link rel="stylesheet" href="/css/calendar.css">
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />
</head>


<div class="container-fluid" style="position: relative">
    <h3 class="h3" style="margin-top: 20px">
        <i class="far fa-calendar-alt"></i> Calendar
    </h3>
    <% List<String> calendarView = (List<String>) request.getAttribute("calendarView"); %>
    <% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>
    <% Map<String, List<LocalDate>> mapUsersAcceptedDaysOff = (Map<String, List<LocalDate>>) request.getAttribute("acceptedHolidays");%>
    <% Map<String, List<LocalDate>> mapUsersNotAcceptedDaysOff = (Map<String, List<LocalDate>>) request.getAttribute("notAcceptedHolidays");%>
    <% List<String> accountableWorkingDays = (List<String>) request.getAttribute("accountableWorkingDays");%>
    <% int numberOfDaysOf = (int) request.getAttribute("numberOfDaysOff");%>
    <div id="calendar" class="container-fluid calendar">
        <br>

        <table class="calendar" id="calendarTable" cellspacing="0" cellpadding="0">
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
            <tbody>
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
                    <button type="button"
                            class="button button-row-weekday"
                            data-toggle="modal"
                            data-target="#modalPlaceHolidayRequest"
                            data-start="<%=date.substring(date.length()-10)%>"
                            <%
                                String endDate;
                                if (accountableWorkingDays.size() < accountableWorkingDays.indexOf(date.substring(date.length() - 10)) + numberOfDaysOf) {
                                    endDate = LocalDate.now().plusDays(365).toString();
                                } else if (numberOfDaysOf > 1) {
                                    endDate = accountableWorkingDays.get(accountableWorkingDays.indexOf(date.substring(date.length() - 10)) + numberOfDaysOf - 1);
                                } else {
                                    endDate = date.substring(date.length() - 10);
                                }
                            %>
                            data-end="<%=endDate%>"
                            <%if(!request.getSession().getAttribute("username").equals(users.get(i).getEmail()) | numberOfDaysOf==0){%>disabled<%}%>>
                    </button>


                </td>

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
            <tfoot>

            </tfoot>
        </table>
    </div>
</div>

<%@include file="placeHolidayRequestDay.jsp" %>
<%@include file="withdrawHolidayRequestDay.jsp" %>


<script>
    $('#modalPlaceHolidayRequest').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var startDate = button.data('start')
        var endDate = button.data('end')

        document.getElementById("StartDate").setAttribute("min", button.data('start'))
        document.getElementById("StartDate").setAttribute("value", button.data('start'))

        document.getElementById("EndDate").setAttribute("min", button.data('start'))
        document.getElementById("EndDate").setAttribute("value", button.data('start'))
        document.getElementById("EndDate").setAttribute("max", button.data('end'))

        var modal = $(this)
    })

</script>
