<%@ page import="com.infoshareacademy.repository.UserRepository" %>
<%@ page import="com.infoshareacademy.model.User" %>
<div class="container-fluid">

    <div class="container-fluid" style="overflow: auto">
        <br>
        <h3>Search user:</h3>
        <input class="form-control" id="myInput" type="text" placeholder="Search.."><br>
        <%
            UserRepository userRepository = new UserRepository();
            userRepository.fillUsersList();
        %>

        <table id="userTable" class="table table-striped table-sm m-1 p-1" cellspacing="0", width="100%">
            <thead>
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Name
                </th>
                <th>
                    Last name
                </th>
                <th>
                    Email
                </th>
                <th>
                    Days off left
                </th>
            </tr>
            </thead>

            <tbody id="userTbody">
            <%
                for (User user: userRepository.getUsersList()) {
            %>
                <tr>
                    <td>
                        <%= user.getId()%>
                    </td>
                    <td>
                        <%= user.getFirstName()%>
                    </td>
                    <td>
                        <%= user.getLastName()%>
                    </td>
                    <td>
                        <%= user.getEmail()%>
                    </td>
                    <td>
                        <%= user.getDaysOffLeft()%>
                    </td>
                    <td>
                        <button class="w3-btn w3-ripple" ng-click="editUser(user.id)">&#9998; Edit</button>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <br>
            <button type="button" class="btn btn-outline-primary" style="border-radius: 0%">Add</button>
            <button type="button" class="btn btn-outline-warning" style="border-radius: 0%">Edit</button>
            <button type="button" id="deleteButton" class="btn btn-outline-danger" style="border-radius: 0%">Delete</button>
    </div>
</div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#userTable tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>