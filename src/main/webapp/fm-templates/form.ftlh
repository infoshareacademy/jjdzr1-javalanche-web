<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/forms.css">
</head>
<body>
<div class="container">
    <form action="/forms" method="post">
        <label class="text">Add user request</label>
        <div class="form-group form-text-input">
            <input name="addUserEmail" type="email" class="form-control" id="inputEmail4" placeholder="Email">
        </div>
        <div class="form-group form-text-input">
            <input name="addUserPassword" type="password" class="form-control" id="inputPassword4"
                   placeholder="Password">
        </div>
        <div class="form-group form-text-input">
            <input name="addUserVerifyPassword" type="password" class="form-control" id="inputPassword4"
                   placeholder="Repeat Password">
        </div>
        <div class="form-group form-text-input">
            <input name="addUserFirstName" type="inputFirstName" class="form-control" id="inputFirstName"
                   placeholder="First Name">
        </div>
        <div class="form-group form-text-input">
            <input name="addUserSurname" type="inputSurname" class="form-control" id="inputSurname"
                   placeholder="Surname">
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit" class="btn btn-dark button-position">Submit
            </button>
            <button class="button-position btn btn-dark" type="reset" class="btn btn-dark button-position">Reset
            </button>
        </div>
    </form>
</div>
<div style="height: 100px">

</div>
<div class="container">
    <form action="/forms" method="post">
        <label>Place holiday request</label>
        <div class="form-group form-date-input">
            <input class="form-control" type="date" id="startDay" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2018-12-31">
        </div>
        <div class="form-group form-date-input">
            <input class="form-control" type="date" id="endDay" name="trip-end"
                   value="2018-07-22"
                   min="2018-01-01" max="2018-12-31">
        </div>

        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit" class="btn btn-dark button-position">Submit
            </button>
            <button class="button-position btn btn-dark" type="reset" class="btn btn-dark button-position">Reset
            </button>
        </div>
    </form>
</div>
</body>

<#--FORMS REQUIRED-->
<#--ADMIN-->
<#--verify holiday request-->
<#--accept user-->
<#--USER-->
<#--holiday request-->