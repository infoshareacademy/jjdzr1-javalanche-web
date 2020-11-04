<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>

<div class="modal fade" id="viewTeamModal<%=team.getId()%>" tabindex="-1" role="form">
    <div class="modal-dialog" role="form">

        <div class="modal-content">
            <div class="modal-header">
                <h5>Employees in team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-striped" id="teamsTable" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th scope="row">E-mail</th>
                        <th scope="row">First name</th>
                        <th scope="row">Last name</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                            for (UserDto userInTeam : usersInTeam) {
                                if (team.getId() == userInTeam.getTeam().getId()) {%>

                        <tr>
                        <td><%=userInTeam.getEmail()%>
                        </td>
                        <td><%=userInTeam.getFirstName()%>
                        </td>
                        <td><%=userInTeam.getLastName()%>
                        </td>
                        </tr>
                                <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <input type="text" name="userId" value="1" hidden>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

            </div>
        </div>
    </div>
</div>