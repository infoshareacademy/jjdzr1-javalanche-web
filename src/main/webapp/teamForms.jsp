<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% int levelOfAccess = (int) request.getAttribute("levelOfAccess"); %>

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


        <%@ include file="features/forms/teamsForms/teamsCollapse.jsp" %>


    </div>

</div>

</body>

</html>
