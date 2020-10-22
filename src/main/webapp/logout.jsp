<%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 15.09.2020
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="./features/favicon.jsp" %>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<link rel="stylesheet" href="/css/index.css">
<div class="main">

    <div style="text-align: center; padding: 70px 0;">

        <div class="logo">
            <img src="/images/javalanche2.jpg" alt="logo" width="360" height="150"/>
            <div class="clearfix"></div>
        </div>
        <div class="middle" style="text-align: center; color: whitesmoke">
            <h3>You have been successfully logged out.<br>
                See you soon!</h3><br>

        </div>
        <p><br></p>
        <div class="back-to-login" style="nav-down: auto; ">
            <p>
                <a href="/login">Back to login page</a>
            </p>
        </div>


    </div>

</div>
</body>
</html>
