

<%  List<UserDto> usersWithoutTeam = (List<UserDto>) request.getAttribute("usersWithoutTeam");
    List<UserDto> teamLeadersWithTeam = (List<UserDto>) request.getAttribute("teamLeadersWithTeam");
    UserDto loggedTeamLeader = (UserDto) request.getAttribute("loggedUser");
%>

<form action="/addUsersToTeamForm" method="post">
    <fieldset <%if (!loggedTeamLeader.isTeamLeader() & loggedTeamLeader.getLevelOfAccess()!=3 | teamLeadersWithTeam.size()==0){%>disabled<%}%>>
        <div class="col-md-6">
            <div class="form-group">
                <label for="exampleFormControlSelect2">Add employees to a team:</label>
                <select multiple class="form-control" id="exampleFormControlSelect2" name="selectedUsersForTeam">
                    <%for (UserDto user : usersWithoutTeam) {%>
                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                    </option>
                    <%}%>
                </select>
            </div>
            <%if(loggedTeamLeader.getLevelOfAccess()==3){%>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Select team for an employee:</label>
                <select class="form-control" id="exampleFormControlSelect1" name="assignUserToThisTeamLeader">
                    <%for(UserDto user : teamLeadersWithTeam){%>
                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " +
                            user.getTeam().getName()%></option>
                    <%}%>
                </select>
            </div>
            <%}%>
            <div class="button-container">
                <button class="button-position btn btn-dark" type="submit">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
