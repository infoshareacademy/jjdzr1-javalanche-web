<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% int levelOfAccess = (int) request.getAttribute("levelOfAccess"); %>

<html lang="pl">
<title>Holiday calendar</title>
<head>
    <%@ include file="../../../../webapp/features/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="../../../../webapp/template/sidebar.jsp" %>

    <div id="page-content-wrapper">
        <%@ include file="../../../../webapp/template/headerbar.jsp" %>


        <%@ include file="forms/holidayForms/holidaysCollapse.jsp" %>


    </div>

</div>

</body>

</html>
