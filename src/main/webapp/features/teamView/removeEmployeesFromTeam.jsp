<%List<UserDto> employeesInTeam = (List<UserDto>) request.getAttribute("employeesInTeam");%>


<div class="modal fade" id="removefromteammodal" tabindex="-1" role="form">
    <div class="modal-dialog" role="form">

        <form method="post" action="/removefromteam">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Remove employees from team:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <select multiple class="form-control" id="selectedUsersToRemoveFromTeam"
                            name="selectedUsersToRemoveFromTeam">
                        <%for (UserDto user : employeesInTeam) {%>
                        <option value=<%=user.getId()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Remove</button>
                </div>
            </div>
        </form>
    </div>
</div>