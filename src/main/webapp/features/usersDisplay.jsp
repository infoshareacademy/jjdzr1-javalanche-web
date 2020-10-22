<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>

<div class="container-fluid">
        <div class="container-fluid" style="overflow: auto">
            <br>
            <h3>
                <i class="fas fa-search-plus"></i> Search for employee:
            </h3>
            <input class="form-control" id="myInput" type="text" placeholder="Type here..."><br>
            <table class="table table-bordered table-sm m-1 p-1" cellspacing="0" width="100%">
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Days off left</th>
                </tr>
                <tbody id="usersTable">
                <%  int i=1;
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
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
</div>