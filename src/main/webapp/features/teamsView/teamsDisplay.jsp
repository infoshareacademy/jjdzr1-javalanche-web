<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="container-fluid" style="overflow: auto">
        <br>
        <h3>List of teams:</h3>
        <br>
        <h4>
            <i class="fas fa-search-plus"></i> Search for teams:
        </h4>
        <input class="form-control" id="myInput" type="text" placeholder="Type here..."><br>
        <table class="table table-striped" cellspacing="0" width="100%">
            <tr>
                <th scope="row">#</th>
                <th scope="row">Team's name</th>
                <th scope="row">Team leader's email</th>
                <th scope="row">Team leader's first name</th>
                <th scope="row">Team leader's last name</th>
                <th scope="row">Action</th>
            </tr>
            <tbody id="usersTable">
            <% int i = 1;%>
            <% for (TeamDto team : teams) { %>
            <tr>
                <td><%=i++%>
                </td>
                <td><%=team.getName()%>
                </td>
                <td><%=team.getTeamLeader().getEmail()%>
                </td>
                <td><%=team.getTeamLeader().getFirstName()%>
                </td>
                <td><%=team.getTeamLeader().getLastName()%>
                </td>
                <td>
                    <div class="row">
                        <div class="btn-toolbar" role="toolbar">
                            <div class="btn-group mr-2" role="group">
                                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                        data-target="#editModal<%=team.getId()%>">Edit team
                                </button>
                            </div>
                            <div class="btn-group mr-2" role="group">
                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                        data-target="#removeModal<%=team.getId()%>"
                                        id="removeFromTeamButton<%=team.getId()%>">Remove team
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
</div>

<% for (TeamDto team : teams) { %>
<div class="modal fade" id="removeModal<%=team.getId()%>" tabindex="-1" role="form">
    <div class="modal-dialog" role="form">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Remove team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Name: <%=team.getName()%> / Team Leader: <%=team.getTeamLeader().getFirstName()%> <%=team.getTeamLeader().getLastName()%>
            </div>
            <div class="modal-footer">
                <form method="post" action="/removeteam">
                    <input type="text" name="removeTeamsLeader" value="<%=team.getId()%>" hidden>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Remove</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editModal<%=team.getId()%>" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Edit user:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form method="post" action="#" autocomplete="off" id="editUserForm<%=team.getId()%>">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Name: </span>
                                </div>
                                <input type="text" name="name" value="<%=team.getName()%>" class="form-control col-sm-8"
                                       aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Name"
                                       required>

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

<% } %>

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Add new team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form autocomplete="off" method="post" action="/addteam" id="addTeamForm">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Name: </span>
                                </div>
                                <input type="text" name="addTeamName" class="form-control" id="inputTeamName"
                                       placeholder="Enter team's name" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Team leader: </span>
                                </div>
                                <select class="form-control" name="addTeamsLeader" id="inputTeamsLeader">
                                    <%for (UserDto user : teamLeaders) {%>
                                    <option value=<%=user.getId()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                                    </option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="addNewUserButton" <%if (teamLeaders.isEmpty()) {%>disabled<%}%>>Add</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="row justify-content-sm-end">
    <div class="col-md-2 .ml-md-auto">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addUserModal">Add new team
        </button>
    </div>
</div>
