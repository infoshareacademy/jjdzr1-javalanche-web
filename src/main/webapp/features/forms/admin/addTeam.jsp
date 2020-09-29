<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>

<% List<UserDto> teamLeaders = (List<UserDto>) request.getAttribute("teamLeadersWithoutTeam");%>

<form action="/forms?addTeam" method="post">
    <div class="col-md-6">
        <div class="form-group">
            <label for="inputTeamName">Team's name:</label>
            <input type="text" name="addTeamName" class="form-control" id="inputTeamName" placeholder="Enter team's name" required>
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect1">Select team leader for the team:</label>
            <select class="form-control" id="exampleFormControlSelect1" name="assignTeamLeaderToGroup">
                <%for(UserDto user : teamLeaders){%>
                <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%></option>
                <%}%>
            </select>
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit">Submit</button>
        </div>
    </div>
</form>
