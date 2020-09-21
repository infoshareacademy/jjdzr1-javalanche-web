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

        <%
            switch (levelOfAccess) {
                case 1:
        %>
        <%@ include file="features/forms/employee/employeeCollapse.jsp" %>
        <%
                break;
            case 2:
        %>
        <%@ include file="features/forms/teamLeader/teamLeaderCollapse.jsp" %>
        <%
                break;
            case 3:
        %>
        <%@ include file="features/forms/admin/adminCollapse.jsp" %>
        <%
                    break;
            }%>

    </div>

</div>

</body>

</html>
