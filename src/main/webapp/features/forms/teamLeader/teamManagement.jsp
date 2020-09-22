<% List<UserDto> usersWithoutTeam = (List<UserDto>) request.getAttribute("usersWithoutTeam");%>

<form action="/forms?addUsersToTeam" method="post">
    <div class="form-group">
        <label for="exampleFormControlSelect2">Example multiple select</label>
        <select multiple class="form-control" id="exampleFormControlSelect2">
            <%for(UserDto user : usersWithoutTeam){%>
            <option value=<%=user.getId()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%></option>
            <%}%>
        </select>
    </div>
    <div class="button-container">
        <button class="button-position btn btn-dark" type="submit">Submit</button>
    </div>
</form>
