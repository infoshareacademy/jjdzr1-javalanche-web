<div class="col-md-6">
    <form action="/changePasswordForm" method="post">
        <h5 class="h3">Change password</h5>
        <div class="form-group">
            <label for="inputOldPassword">Old Password:</label>
            <input type="password" name="accountOldPassword" class="form-control" id="inputOldPassword"
                   placeholder="Enter old password" required>
        </div>
        <div class="form-group">
            <label for="inputNewPassword">New Password:</label>
            <input type="password" name="accountNewPassword" class="form-control" id="inputNewPassword"
                   placeholder="Enter new password" required>
        </div>
        <div class="form-group">
            <label for="inputNewPasswordAgain">New Password Again:</label>
            <input type="password" name="accountNewPasswordRepeated" class="form-control" id="inputNewPasswordAgain"
                   placeholder="Enter new password again" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
