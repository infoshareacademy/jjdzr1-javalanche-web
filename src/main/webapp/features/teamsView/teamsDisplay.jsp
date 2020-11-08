<%@ page import="com.infoshareacademy.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="container-fluid" style="overflow: auto">
        <br>
        <h3>Teams: </h3>
        <br>
        <table class="table table-striped" id="teamsTable" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th scope="row">Name</th>
                <th scope="row">E-mail</th>
                <th scope="row">First name</th>
                <th scope="row">Last name</th>
                <th scope="row">Action</th>
            </tr>
            </thead>
            <tbody>
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
                                <button type="button" class="btn btn-info btn-sm" data-toggle="modal"
                                        data-target="#viewTeamModal<%=team.getId()%>"
                                        id="viewTeamButton<%=team.getId()%>">
                                    View team
                                </button>

                                <%@ include file="viewEmployeesToTeam.jsp" %>
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
            <tfoot>
            <tr>
                <th scope="row">#</th>
                <th scope="row">Name</th>
                <th scope="row">E-mail</th>
                <th scope="row">First name</th>
                <th scope="row">Last name</th>
                <th scope="row">Action</th>
            </tr>
            </tfoot>
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
                Name: <%=team.getName()%> / Team
                Leader: <%=team.getTeamLeader().getFirstName()%> <%=team.getTeamLeader().getLastName()%>
            </div>
            <div class="modal-footer">
                <form method="post" action="/removeteam">
                    <input type="text" name="removeTeamsLeader" value="<%=team.getTeamLeader().getId()%>" hidden>
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
                <h5>Edit team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form method="post" action="editteam">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Name: </span>
                                </div>
                                <input type="text" id="editedName" name="editedName" value="<%=team.getName()%>"
                                       class="form-control col-sm-8"
                                       aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Name"
                                       required>
                            </div>

                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Team leader: </span>
                                </div>

                                <select class="form-control" id="exampleFormControlSelect1" id="editTeamLeader"
                                        name="editTeamLeader" class="form-control col-sm-8">
                                    <option value="<%=team.getTeamLeader().getEmail()%>"><%=team.getTeamLeader().getFirstName() + " " + team.getTeamLeader().getLastName() + " " + team.getTeamLeader().getId()%>
                                    </option>
                                    <%for (UserDto user : teamLeaders) {%>
                                    <option value=<%=user.getEmail()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                                    </option>
                                    <%}%>
                                </select>
                                <input type="text" name="editedTeamId" value="<%=team.getId()%>" hidden>
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


<%@include file="addTeamModal.jsp" %>

