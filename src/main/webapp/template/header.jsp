<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Holiday calendar</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="../css/simple-sidebar.css" rel="stylesheet">

    <!-- Custom styles for table -->
    <link href="../css/table_date.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/forms.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <%@ include file="/features/favicon.jsp" %>
    <!-- DataTables -->
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4-4.1.1/jq-3.3.1/dt-1.10.22/datatables.min.css"/>

</head>
<body>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
        src="https://cdn.datatables.net/v/bs4-4.1.1/jq-3.3.1/dt-1.10.22/datatables.min.js"></script>


<div class="d-flex" id="wrapper">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">
            <img src="/images/javalanche2.jpg" alt="logo" width="144" height="60"/>
        </div>
        <div class="list-group list-group-flush">
            <a href="/main" id="main" class="list-group-item list-group-item-action bg-light"> <i
                    class="far fa-calendar-alt"></i> Calendar view</a>
            <a href="/employees" id="employees" class="list-group-item list-group-item-action bg-light"><i
                    class="fas fa-user-alt"></i> Employees</a>

            <c:if test="${sessionScope.levelOfAccess==2}">
                <a href="/team" id="team" class="list-group-item list-group-item-action bg-light">
                    <i class="fas fa-user-friends"></i> Team</a>
            </c:if>
            <c:if test="${sessionScope.levelOfAccess==3}">
                <a href="/teams" id="teams" class="list-group-item list-group-item-action bg-light">
                    <i class="fas fa-users"></i> Teams</a>
                <a href="/holidays" id="team" class="list-group-item list-group-item-action bg-light">
                    <i class="fab fa-algolia"></i> Holidays</a>
                <a href="/management" id="management" class="list-group-item list-group-item-action bg-light"><i
                        class="fas fa-cog"></i> Management</a>
            </c:if>
        </div>
    </div>

    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark border-bottom">
            <button class="btn btn-outline-light" id="menu-toggle">Menu</button>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <%=request.getSession().getAttribute("firstName")%> <%=request.getSession().getAttribute("lastName")%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/settings">Settings</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
