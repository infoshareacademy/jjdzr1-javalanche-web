<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/forms.css">
</head>
<body>
<div class="container">
    <form action="/forms" method="post">
            <div class="row">
                <div class="form-group form-field-width">
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                </div>
                <div class="form-group form-field-width">
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
            </div>
            <div class="row">
                <div>
                    <input class="form-control form-field-width" type="text" placeholder="First Name">
                </div>
                <div>
                    <input class="form-control form-field-width" type="text" placeholder="Surname">
                </div>
            </div>
            <div class="row">
                <button type="reset" class="btn btn-dark form-field-width">Reset</button>
                <button type="submit" class="btn btn-dark form-field-width">Submit</button>
            </div>
    </form>

    <form action="/forms" method="post">
        <div class="row">
            <div>
                <input class="form-control form-field-width" type="date" id="startDay" name="trip-start"
                       value="2018-07-22"
                       min="2018-01-01" max="2018-12-31">
            </div>
            <div>
                <input class="form-control form-field-width" type="date" id="endDay" name="trip-end"
                       value="2018-07-22"
                       min="2018-01-01" max="2018-12-31">
            </div>
        </div>
        <div class="row">
            <button type="reset" class="btn btn-dark form-field-width">Reset</button>
            <button type="submit" class="btn btn-dark form-field-width">Submit</button>
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