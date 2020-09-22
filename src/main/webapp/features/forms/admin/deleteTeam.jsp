<%@ page import="com.infoshareacademy.DTO.TeamDto" %>

<% List<TeamDto> teams = (List<TeamDto>) request.getAttribute("teamsList");%>

<form action="/forms?deleteTeam" method="post">
    <div class="col-md-6">
        <div class="form-group">
            <label for="exampleFormControlSelect1">Select user to remove</label>
            <select class="form-control" id="exampleFormControlSelect1" name="selectedTeamIdToDelete">
                <%for(TeamDto team : teams){%>
                <option value=<%=team.getId()%>><%=team.getId() + " " + team.getName() + " " + team.getTeamLeader().getFirstName() + " " + team.getTeamLeader().getLastName()%></option>
                <%}%>
            </select>
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit">Submit</button>
        </div>
    </div>
</form>
