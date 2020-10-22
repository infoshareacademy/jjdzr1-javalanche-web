<div class="col-md-6">
    <form action="/changeEmailForm" method="post">
        <h5 class="h3">Change e-mail:</h5>
        <div class="form-group">
            <label for="labelOldEmail">Old E-mail Address:</label>
            <input class="form-control" type="text" placeholder=<c:out value="${sessionScope.username}"></c:out> }" id="labelOldEmail" readonly>
        </div>
        <div class="form-group">
            <label for="inputPasswordForVerification">Password:</label>
            <input type="password" name="accountsPassword" class="form-control" id="inputPasswordForVerification"
                   placeholder="Enter your password" required>
        </div>
        <div class="form-group">
            <label for="inputNewEmailAddress">New E-mail Address:</label>
            <input type="email" name="newAccountsEmail" class="form-control" id="inputNewEmailAddress" aria-describedby="emailHelp"
                   placeholder="Enter your new e-mail address">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

