<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<title>Holiday calendar</title>
<head>
    <%@ include file="features/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="template/sidebar.jsp" %>

    <div id="page-content-wrapper">
        <%@ include file="template/headerbar.jsp" %>


        <div class="container-fluid">

            <%@ include file="features/usersDisplay.jsp" %>

            <div class="row justify-content-sm-end">
                <div class="col-md-2 .ml-md-auto">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addUserModal">Add new user</button>
                </div>
            </div>
        </div>


    </div>

</div>

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Add new user:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form autocomplete="off" method="post" action="/adduser" id="addUserForm">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Name: </span>
                                </div>
                                <input type="text" name="name" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Name" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Last name: </span>
                                </div>
                                <input type="text" name="lastName" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Lastname" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">E-mail: </span>
                                </div>
                                <input type="email" name="email" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Email" required>
                            </div>

                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">User type: </span>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" name="levelOfAccess" id="radioButtonAdmin" class="form-control-input" value="3"><label class="form-control-label" for="radioButtonAdmin">Admin</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" name="levelOfAccess" id="radioButtonTeamLeader" class="form-control-input" value="2"><label class="form-control-label" for="radioButtonTeamLeader">Team leader</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" name="levelOfAccess" id="radioButtonEmployee" class="form-control-input" value="1" checked><label class="form-control-label" for="radioButtonEmployee">Employee</label>
                                </div>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Days off left: </span>
                                </div>
                                <input type="number" name="daysOff" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Enter number of remaining days off" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Password:</span>
                                </div>
                                <input type="password" name="password" id="password" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required autocomplete="new-password">
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Retype password:</span>
                                </div>
                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control col-sm-8" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Info:</span>
                                </div>
                                <span id="message" class="form-control col-sm-8"></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="addNewUserButton" disabled>Add</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    $('#password, #confirmPassword').on('keyup', function () {
        $('#message').html('password doesn\'t match!').css('color', 'red');

        if ($('#password').val().length == 0 || $('#confirmPassword').val().length ==0){
            document.getElementById("addNewUserButton").disabled = true;

        } else if ($('#password').val() == $('#confirmPassword').val()) {
            $('#message').html('password matches!').css('color', 'green');
            document.getElementById("addNewUserButton").disabled = false;
        } else {
            document.getElementById("addNewUserButton").disabled = true;
        }
    });
</script>