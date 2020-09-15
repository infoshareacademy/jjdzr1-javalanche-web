<%--
  Created by IntelliJ IDEA.
  User: kacper-kwiatkowski
  Date: 15.09.2020
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/forms.css">
</head>
<body>
<div id="page-content-wrapper">

    <%@ include file="sidebar.jsp"%>
    <%@ include file="topbar.jsp"%>
</div>


<div class="container">
    <form action="/forms" method="post">
        <label>Add user request</label>
        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
            </div>
            <div class="form-group col-md-6">
                <input type="password" class="form-control" id="inputPassword4" placeholder="Password">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="inputFirstName" class="form-control" id="inputFirstName" placeholder="First Name">
            </div>
            <div class="form-group col-md-6">
                <input type="inputSurname" class="form-control" id="inputSurname" placeholder="Surname">
            </div>
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit" class="btn btn-dark button-position">Submit</button>
            <button class="button-position btn btn-dark" type="reset" class="btn btn-dark button-position">Reset</button>
        </div>
    </form>

    <form action="/forms" method="post">
        <p>.</p>
        <p>.</p>
        <p>.</p>
        <label>Place holiday request</label>
        <div class="form-row">
            <div class="form-group col-md-6">
                <input class="form-control form-field-width form-group" type="date" id="startDay" name="trip-start"
                       value="2018-07-22"
                       min="2018-01-01" max="2018-12-31">
            </div>
            <div class="form-group col-md-6">
                <input class="col form-control form-field-width form-group" type="date" id="endDay" name="trip-end"
                       value="2018-07-22"
                       min="2018-01-01" max="2018-12-31">
            </div>
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit" class="btn btn-dark button-position">Submit</button>
            <button class="button-position btn btn-dark" type="reset" class="btn btn-dark button-position">Reset</button>
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
</html>
