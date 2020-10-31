<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%  List<UserDto> usersWithoutTeam = (List<UserDto>) request.getAttribute("usersWithoutTeam");%>

<div class="modal fade" id="addtoteammodal" tabindex="-1" role="form">
    <div class="modal-dialog" role="form">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Add employees to team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <select multiple class="form-control" id="exampleFormControlSelect2" name="selectedUsersForTeam">
                    <%for (UserDto user : usersWithoutTeam) {%>
                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                    </option>
                    <%}%>
                </select>
            </div>
            <div class="modal-footer">
                <form method="post" action="/addtoteam">
                    <input type="text" name="userId" value="1" hidden>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>