<form action="/addUserForm" method="post">
    <div class="col-md-6">

        <div class="form-group">
            <label for="inputEmail">Email address:</label>
            <input type="email" name="addUserEmail" class="form-control" id="inputEmail" placeholder="Enter email"
                   required>
        </div>
        <div class="form-group">
            <label for="inputEmail">Password:</label>
            <input type="password" name="addUserPassword" class="form-control" id="inputPassword"
                   placeholder="Enter password" required>
        </div>
        <div class="form-group">
            <label for="inputEmail">First name:</label>
            <input type="text" name="addUserFirstName" class="form-control" id="inputFirstName"
                   placeholder="Enter first name" required>
        </div>
        <div class="form-group">
            <label for="inputEmail">Surname:</label>
            <input type="text" name="addUserSurname" class="form-control" id="inputSurname" placeholder="Enter surname"
                   required>
        </div>
        <div class="form-group">
            <label for="inputEmail">Days off:</label>
            <input type="number" name="addUserDaysOff" class="form-control" id="inputDaysOff"
                   placeholder="Enter number of remaining days off"
                   required>
        </div>

        <fieldset class="form-group ">
            <div class="form-group">
                <label>Account status:</label>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="radio" name="levelOfAccess" value="1" class="form-check-input" checked>Employee
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="radio" name="levelOfAccess" value="2" class="form-check-input">Team Leader
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="radio" name="levelOfAccess" value="3" class="form-check-input">Admin
                    </label>
                </div>

            </div>
        </fieldset>

        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit" value="success">Submit

            </button>
            <button class="button-position btn btn-dark" type="reset">Reset
            </button>

        </div>
    </div>
    <div class="col-md-6">
    </div>
</form>
