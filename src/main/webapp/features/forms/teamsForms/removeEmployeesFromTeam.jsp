<%
    List<UserDto> usersToRemoveFromTeam = (List<UserDto>) request.getAttribute("employeesInTeam");
    List<UserDto> usersToRemoveFromAnyTeam = (List<UserDto>) request.getAttribute("employeesInAnyTeam");
    UserDto loggedUser = (UserDto) request.getAttribute("loggedUser");
%>

<form action="/removeUsersFromTeam" method="post">
    <fieldset <%if (!loggedUser.isTeamLeader() & loggedUser.getLevelOfAccess()!=3){%>disabled<%}%>>
        <div class="col-md-6">
            <div class="form-group">
                <label for="exampleFormControlSelect2">Remove employees from a team:</label>
                <select multiple class="form-control" id="exampleFormControlSelect2"
                        name="selectedUsersToRemoveFromTeam">
                    <%
                        if (loggedUser.getLevelOfAccess()==2) {
                            for (UserDto user : usersToRemoveFromTeam) {
                    %>
                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                    </option>
                    <%
                        }
                    } else {
                        for (UserDto user : usersToRemoveFromAnyTeam) {
                    %>
                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " +
                            user.getId() + " " + user.getTeam().getId() + " " + user.getTeam().getName()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="button-container">
                <button class="button-position btn btn-dark" type="submit">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
