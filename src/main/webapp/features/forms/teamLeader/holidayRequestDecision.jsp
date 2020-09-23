<form action="/forms?holidayRequestDecision" method="post">
    <div class="col-md-6">
        <div class="form-group">
            <label for="exampleFormControlSelect1">Select user to remove</label>
            <select class="form-control" id="exampleFormControlSelect1" name="selectedUserInTeam">
                <%for(TeamDto team : teams){%>
                <option value=<%=team.getId()%>><%=team.getId() + " " + team.getName() + " " + team.getTeamLeader().getFirstName() + " " + team.getTeamLeader().getLastName()%></option>
                <%}%>
            </select>
        </div>
        <fieldset>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Select user to remove</label>
                <select class="form-control" id="exampleFormControlSelect2" name="selectedHolidayRequest">
                    <%for(TeamDto team : teams){%>
                    <option value=<%=team.getId()%>><%=team.getId() + " " + team.getName() + " " + team.getTeamLeader().getFirstName() + " " + team.getTeamLeader().getLastName()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Select user to remove</label>
                <select class="form-control" id="exampleFormControlSelect3" name="holidayRequestDecision">
                <option value="true">ACCEPT</option>
                <option value="false">REJECT</option>

                </select>
            </div>
            <div class="button-container">
                <button class="button-position btn btn-dark" type="submit">Submit</button>
            </div>
        </fieldset>
    </div>
</form>