<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>

    <div class="container-fluid" style="overflow: auto">
        <br>
        <h4>
            <i class="fas fa-search-plus"></i> Search for employee:
        </h4>
        <input class="form-control" id="myInput" type="text" placeholder="Type here..."><br>
        <table class="table table-striped" cellspacing="0" width="100%">
            <tr>
                <th scope="row">#</th>
                <th scope="row">Name</th>
                <th scope="row">Last Name</th>
                <th scope="row">Email</th>
                <th scope="row">Days off left</th>
                <th scope="row">Team name</th>
                <th scope="row">Action</th>
            </tr>
            <tbody id="usersTable">
            <% int i = 1;
                for (UserDto user : users) { %>
            <tr>
                <td><%=i++ %>
                </td>
                <td><%=user.getFirstName() %>
                </td>
                <td><%=user.getLastName() %>
                </td>
                <td><%=user.getEmail() %>
                </td>
                <td><%=user.getDaysOffLeft() %>
                </td>
                <% if (user.getTeam() == null || user.getTeam().getName() == null) { %>
                <td>Not assigned</td>
                <% } else { %>
                <td><%=user.getTeam().getName()%></td>
                <% } %>
                <td>
                    <div class="row">
                        <div class="btn-toolbar" role="toolbar">
                            <div class="btn-group mr-2" role="group">
                                <button type="submit" class="btn btn-primary btn-sm" data-toggle="modal"
                                        data-target="#editModal<%=user.getId()%>">Edit user
                                </button>
                            </div>
                            <div class="btn-group mr-2" role="group">
                                <button type="submit" class="btn btn-warning btn-sm" data-toggle="modal"
                                        data-target="#editPassword<%=user.getId()%>">Change password
                                </button>
                            </div>
                            <div class="btn-group mr-2" role="group">
                                <button type="submit" class="btn btn-danger btn-sm" data-toggle="modal"
                                        data-target="#deleteModal<%=user.getId()%>">Delete user
                                </button>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

<% for (UserDto user: users) { %>
    <div class="modal fade" id="deleteModal<%=user.getId()%>" tabindex="-1" role="form">
        <div class="modal-dialog" role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Delete user:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <% if (user.getTeam() != null) { %>
                <div class="modal-body">
                    <p><%=user.getFirstName()%> <%=user.getLastName()%> is assigned to <%=user.getTeam().getName()%></p>
                    <p>Please remove user from team first.</p>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                <% } else if (user.getEmail().equals(request.getSession().getAttribute("username"))) { %>
                <div class="modal-body">
                    <p>You cannot remove yourself</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                <% } else { %>
                <div class="modal-body">
                    <%=user.getFirstName()%> <%=user.getLastName()%>
                </div>
                <div class="modal-footer">
                    <form method="post" action="/deleteUser">
                        <input type="text" name="userId" value="<%=user.getId()%>" hidden>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
                <% } %>
            </div>
        </div>
    </div>

<div class="modal fade" id="editModal<%=user.getId()%>" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Edit user:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form method="post" action="/edituser" autocomplete="off" id="editUserForm<%=user.getId()%>">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Name: </span>
                                </div>
                                <input type="text" name="name" value="<%=user.getFirstName()%>" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Name" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Last name: </span>
                                </div>
                                <input type="text" name="lastName" value="<%=user.getLastName()%>" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Lastname" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">E-mail: </span>
                                </div>
                                <input type="email" name="email" value="<%=user.getEmail()%>" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Email" required>
                            </div>

                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">User type: </span>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" name="levelOfAccess" id="radioButtonAdmin<%=user.getId()%>" class="form-control-input" value="3"><label class="form-control-label" for="radioButtonAdmin<%=user.getId()%>">Admin</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" name="levelOfAccess" id="radioButtonTeamLeader<%=user.getId()%>" class="form-control-input" value="2"><label class="form-control-label" for="radioButtonTeamLeader<%=user.getId()%>">Team leader</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" name="levelOfAccess" id="radioButtonEmployee<%=user.getId()%>" class="form-control-input" value="1"><label class="form-control-label" for="radioButtonEmployee<%=user.getId()%>">Employee</label>
                                </div>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Days off left: </span>
                                </div>
                                <input type="number" name="daysOff" value="<%=user.getDaysOffLeft()%>" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Enter number of remaining days off" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>


<div class="modal fade" id="editPassword<%=user.getId()%>" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Change password:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form method="post" action="/editpassword" autocomplete="off" id="editUserForm<%=user.getId()%>">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Password:</span>
                                </div>
                                <input type="password" name="password" id="password<%=user.getId()%>" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required autocomplete="new-password">
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Retype password:</span>
                                </div>
                                <input type="password" name="confirmPassword" id="confirmPassword<%=user.getId()%>" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Info:</span>
                                </div>
                                <span id="message<%=user.getId()%>" class="form-control col-sm-8"></span>
                                <input type="text" name="changePasswordUserId" value="<%=user.getId()%>" hidden>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="updateButton<%=user.getId()%>" disabled>Change password</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    document.getElementById("editModal<%=user.getId()%>").onclick = functionRadioButton();
    function functionRadioButton() {
        if ((<%=user.getLevelOfAccess()%>) == 1) {
            document.getElementById("radioButtonEmployee<%=user.getId()%>").checked = true;
        } else if ((<%=user.getLevelOfAccess()%>) == 2) {
            document.getElementById("radioButtonTeamLeader<%=user.getId()%>").checked = true;
        } else if ((<%=user.getLevelOfAccess()%>) == 3) {
            document.getElementById("radioButtonAdmin<%=user.getId()%>").checked = true;
        }
    }

</script>
<script>
    $('#password<%=user.getId()%>, #confirmPassword<%=user.getId()%>').on('keyup', function () {
        $('#message<%=user.getId()%>').html('password doesn\'t match!').css('color', 'red');

        if ($('#password<%=user.getId()%>').val().length == 0 || $('#confirmPassword<%=user.getId()%>').val().length ==0){
            document.getElementById("addNewUserButton<%=user.getId()%>").disabled = true;

        } else if ($('#password<%=user.getId()%>').val() == $('#confirmPassword<%=user.getId()%>').val()) {
            $('#message<%=user.getId()%>').html('password matches!').css('color', 'green');
            document.getElementById("updateButton<%=user.getId()%>").disabled = false;
        } else {
            document.getElementById("updateButton<%=user.getId()%>").disabled = true;
        }
    });
</script>
<% } %>