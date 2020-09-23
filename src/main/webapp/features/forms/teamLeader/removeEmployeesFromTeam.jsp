<%
    List<UserDto> usersToRemoveFromTeam = (List<UserDto>) request.getAttribute("employeesInTeam");
    UserDto loggedTeamLeader = (UserDto) request.getAttribute("loggedUser");
%>

<form action="/forms?removeUsersFromTeam" method="post">
    <fieldset <%if (!loggedTeamLeader.isTeamLeader()){%>disabled<%}%>>
        <div class="form-group">
            <label for="exampleFormControlSelect2">Remove employees from a team</label>
            <select multiple class="form-control" id="exampleFormControlSelect2" name="selectedUsersToRemoveFromTeam">
                <%for(UserDto user : usersToRemoveFromTeam){%>
                <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%></option>
                <%}%>
            </select>
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit">Submit</button>
        </div>
    </fieldset>
</form>
