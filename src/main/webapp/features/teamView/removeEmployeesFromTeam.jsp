<%List<UserDto> employeesInTeam = (List<UserDto>) request.getAttribute("employeesInTeam");%>


<div class="modal fade" id="removefromteammodal" tabindex="-1" role="form">
    <div class="modal-dialog" role="form">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Remove employees from team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <select multiple class="form-control" id="exampleFormControlSelect2" name="selectedUsersForTeam">
                    <%for (UserDto user : employeesInTeam) {%>
                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                    </option>
                    <%}%>
                </select>
            </div>
            <div class="modal-footer">
                <form method="post" action="/removefromteam">
                    <input type="text" name="userId" value="1" hidden>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Remove</button>
                </form>
            </div>
        </div>
    </div>
</div>