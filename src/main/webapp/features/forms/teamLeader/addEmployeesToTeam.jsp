<%@ page import="java.util.Optional" %>
<%@ page import="com.infoshareacademy.model.User" %>

<% List<UserDto> usersWithoutTeam = (List<UserDto>) request.getAttribute("usersWithoutTeam");
    UserDto loggedUser = (UserDto) request.getAttribute("loggedUser");
%>


<form action="/forms?addUsersToTeam" method="post">
    <fieldset <%if (!loggedUser.isTeamLeader()){%>disabled<%}%>>
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
            <div class="button-container">
                <button class="button-position btn btn-dark" type="submit">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
