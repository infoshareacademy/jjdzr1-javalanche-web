<%@ page import="javax.inject.Inject" %>
<%@ page import="com.infoshareacademy.service.DayOffService" %>
<%@ page import="com.infoshareacademy.model.Team" %>
<%@ page import="com.infoshareacademy.DAO.UserDao" %>
<% List<UserDto> employeesInTeam = (List<UserDto>) request.getAttribute("employeesInTeam");
    List<DayOffDto> chosenUserHolidayRequests = (List<DayOffDto>) request.getAttribute("holidayRequests");
    UserDto isLoggedTeamLeader = (UserDto) request.getAttribute("loggedUser");
%>

<form action="/forms?holidayRequestDecision" method="post">
    <div class="col-md-6">
        <fieldset <%if (!isLoggedTeamLeader.isTeamLeader()){%>disabled<%}%>>
        <div class="form-group">
            <label for="exampleFormControlSelect2">Select holiday request:</label>
            <select class="form-control" id="exampleFormControlSelect2" name="selectedHolidayRequest">
                <%
                    for (DayOffDto chosenRequest : chosenUserHolidayRequests) {
                        if (employeesInTeam.stream().anyMatch(user -> user.getId() == chosenRequest.getUser().getId()) &
                        !chosenRequest.isAccepted()) {

                %>
                <option value=<%=chosenRequest.getId()%>><%=chosenRequest.getUser().getFirstName() + " " + chosenRequest.getUser().getLastName() + " " + chosenRequest.getId() + " " + chosenRequest.getFirstDay() + " " + chosenRequest.getLastDay()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="exampleFormControlSelect3">Make decision:</label>
            <select class="form-control" id="exampleFormControlSelect3" name="holidayRequestVerdict">
                <option value=true>ACCEPT</option>
                <option value="false">REJECT</option>
            </select>
        </div>


        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit">Submit</button>
        </div>
        </fieldset>

    </div>
</form>