<div class="container-fluid">
    <% List<UserDto> team = (List<UserDto>) request.getAttribute("team"); %>
    <% UserDto teamLeader = (UserDto) request.getAttribute("teamLeader"); %>
    <br>
    <h3><%=teamLeader.getTeam().getName()%>:</h3>
    <br><br>
    <div class="container-fluid">
        <table id="usersTable" class="table table-striped" style="width:100%">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th scope="row">Name</th>
                <th scope="row">Last name</th>
                <th scope="row">E-mail</th>
                <th scope="row">Days off left</th>
                <th scope="row">Action</th>
            </tr>
            </thead>
            <tbody>
            <% int i = 1; %>
            <% for (UserDto userDto : team) { %>
            <tr>
                <td><%=i++%>
                </td>
                <td><%=userDto.getFirstName()%>
                </td>
                <td><%=userDto.getLastName()%>
                </td>
                <td><%=userDto.getEmail()%>
                </td>
                <td><%=userDto.getDaysOffLeft()%>
                </td>
                <td>
                    <div class="row">
                        <div class="btn-toolbar" role="toolbar">
                            <div class="btn-group mr-2" role="group">
                                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                        data-target="#editModal<%=userDto.getId()%>">Edit user
                                </button>
                            </div>
                            <div class="btn-group mr-2" role="group">
                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                        data-target="#removeModal<%=userDto.getId()%>"
                                        id="removeFromTeamButton<%=userDto.getId()%>">Remove from team
                                </button>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <% } %>

            </tbody>
            <tfoot>
            <tr>
                <th scope="row">#</th>
                <th scope="row">Name</th>
                <th scope="row">Last name</th>
                <th scope="row">E-mail</th>
                <th scope="row">Days off left</th>
                <th scope="row">Action</th>
            </tr>
            </tfoot>

        </table>
    </div>

    <% for (UserDto user : team) { %>
    <div class="modal fade" id="removeModal<%=user.getId()%>" tabindex="-1" role="form">
        <div class="modal-dialog" role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Remove user:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <%=user.getFirstName()%> <%=user.getLastName()%>
                </div>
                <div class="modal-footer">
                    <form method="get" action="/removefromteam">
                        <input type="text" name="userId" value="<%=user.getId()%>" hidden>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-danger">Remove</button>
                    </form>
                </div>
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

                <form method="post" action="#" autocomplete="off" id="editUserForm<%=user.getId()%>">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col">
                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend col-sm-4">
                                        <span class="input-group-text col-sm-12">Name: </span>
                                    </div>
                                    <input type="text" name="name" value="<%=user.getFirstName()%>"
                                           class="form-control col-sm-8" aria-label="Small"
                                           aria-describedby="inputGroup-sizing-sm" placeholder="Name" required>
                                </div>
                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend col-sm-4">
                                        <span class="input-group-text col-sm-12">Last name: </span>
                                    </div>
                                    <input type="text" name="lastName" value="<%=user.getLastName()%>"
                                           class="form-control col-sm-8" aria-label="Small"
                                           aria-describedby="inputGroup-sizing-sm" placeholder="Lastname"
                                           required>
                                </div>
                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend col-sm-4">
                                        <span class="input-group-text col-sm-12">E-mail: </span>
                                    </div>
                                    <input type="email" name="email" value="<%=user.getEmail()%>"
                                           class="form-control col-sm-8" aria-label="Small"
                                           aria-describedby="inputGroup-sizing-sm" placeholder="Email" required>
                                </div>

                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend col-sm-4">
                                        <span class="input-group-text col-sm-12">User type: </span>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input type="radio" name="levelOfAccess"
                                               id="radioButtonAdmin<%=user.getId()%>"
                                               class="form-control-input" value="3">
                                        <label class="form-control-label"
                                               for="radioButtonAdmin<%=user.getId()%>">Admin</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input type="radio" name="levelOfAccess"
                                               id="radioButtonTeamLeader<%=user.getId()%>"
                                               class="form-control-input" value="2">
                                        <label class="form-control-label"
                                               for="radioButtonTeamLeader<%=user.getId()%>">Team
                                            leader</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input type="radio" name="levelOfAccess"
                                               id="radioButtonEmployee<%=user.getId()%>"
                                               class="form-control-input" value="1">
                                        <label class="form-control-label"
                                               for="radioButtonEmployee<%=user.getId()%>">Employee</label>
                                    </div>
                                </div>
                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend col-sm-4">
                                        <span class="input-group-text col-sm-12">Days off left: </span>
                                    </div>
                                    <input type="number" name="daysOff" value="<%=user.getDaysOffLeft()%>"
                                           class="form-control col-sm-8" aria-label="Small"
                                           aria-describedby="inputGroup-sizing-sm"
                                           placeholder="Enter number of remaining days off" required>
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

    <% } %>
    <br><br>
    <div class="row justify-content-sm-start">
        <div class="col-sm-2">
            <button type="button" class="btn btn-primary">
                Add user to team
            </button>
        </div>
    </div>

</div>

<script>
    $(document).ready(function () {
        $('#usersTable').DataTable({
            "dom":'<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 row justify-content-end"f>><"row"<"col-sm-12"tr>><"row"<"col-sm-12 col-md-5"i><"col-sm-12 col-md-7"p>>'
        });
        $('#usersTable_filter label').addClass('justify-content-sm-end');
    });
</script>