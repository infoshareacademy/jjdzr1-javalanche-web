<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<form action="/forms" method="post">
    <div class="form-group">
        <label for="name-field">E-Mail</label>
        <input id="name-field" type="text"/>
    </div>
    <div>
        <label for="name-field">Password</label>
        <input id="name-field" type="text"/>
    </div>
    <div>
        <label for="name-field">First Name</label>
        <input id="name-field" type="text"/>
    </div>
    <div>
        <label for="name-field">Last Name</label>
        <input id="name-field" type="text"/>
    </div>
</form>


<form action="/forms" method="post">
    <div>
        <label for="name-field">Name</label>
        <input id="name-field" type="text"/>
    </div>
    <div>
        <input type="date" id="startDay" name="trip-start"
               value="2018-07-22"
               min="2018-01-01" max="2018-12-31">
    </div>
    <div>
        <input type="date" id="endDay" name="trip-end"
               value="2018-07-22"
               min="2018-01-01" max="2018-12-31">
    </div>
    <button type="button">Reset</button>
    <button type="button">Submit</button>
</form>
</body>

<#--FORMS REQUIRED-->
<#--ADMIN-->
<#--verify holiday request-->
<#--accept user-->
<#--USER-->
<#--holiday request-->