<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<% List<UserDto> employeesInTeam = (List<UserDto>) request.getAttribute("employeesInTeam");
    List<DayOffDto> chosenUserHolidayRequests = (List<DayOffDto>) request.getAttribute("holidayRequests");
    UserDto isLoggedTeamLeader = (UserDto) request.getAttribute("loggedUser");
    int levelOfAccess5 = Integer.parseInt(request.getAttribute("levelOfAccess").toString());
%>

<form action="/holidayRequestDecisionForm" method="post">
    <div class="col-md-6">
        <fieldset <%if (!isLoggedTeamLeader.isTeamLeader() & levelOfAccess5!=3){%>disabled<%}%>>
            <div class="form-group">
                <label for="exampleFormControlSelect2">Select holiday request:</label>
                <select class="form-control" id="exampleFormControlSelect2" name="selectedHolidayRequest">
                    <%if(levelOfAccess==2){
                            for (DayOffDto chosenRequest : chosenUserHolidayRequests) {
                                if (employeesInTeam.stream().anyMatch(user -> user.getId() == chosenRequest.getUser().getId()) &
                                        !chosenRequest.isAccepted()) {

                    %>
                    <option value=<%=chosenRequest.getId()%>><%=chosenRequest.getUser().getFirstName() + " " + chosenRequest.getUser().getLastName() + " " + chosenRequest.getId() + " " + chosenRequest.getFirstDay() + " " + chosenRequest.getLastDay()%>
                    </option>
                    <%

                            }
                        }
                    } else {
                        for (DayOffDto anyRequest : chosenUserHolidayRequests) {
                            if (!anyRequest.isAccepted()) {
                    %>
                    <option value=<%=anyRequest.getId()%>><%=anyRequest.getUser().getFirstName() + " " + anyRequest.getUser().getLastName() + " " + anyRequest.getId() + " " + anyRequest.getFirstDay() + " " + anyRequest.getLastDay()%>
                    </option>
                    <% }
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