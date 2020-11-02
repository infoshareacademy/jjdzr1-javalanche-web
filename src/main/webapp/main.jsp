<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<title>Holiday calendar</title>
<head>
    <%@ include file="template/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="template/sidebar.jsp" %>

    <div id="page-content-wrapper" >
        <%@ include file="template/headerbar.jsp" %>

        <%@ include file="features/calendarView/calendar.jsp" %>

        <%@ include file="features/calendarView/placeHolidayRequestDay.jsp" %>

        <%@ include file="features/employeeView/popupUser.jsp" %>

    </div>

</div>

</body>
</html>