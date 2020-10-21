<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int levelOfAccess2 = (int) request.getSession().getAttribute("levelOfAccess"); %>
<!-- Sidebar -->
<div class="bg-light border-right" id="sidebar-wrapper">
    <div class="sidebar-heading">
        <img src="/images/javalanche2.jpg" alt="logo" width="144" height="60"/>
    </div>
    <div class="list-group list-group-flush">
        <a href="/employees" id="employees" class="list-group-item list-group-item-action bg-light">Employees</a>
        <a href="/main" id="main" class="list-group-item list-group-item-action bg-light">Calendar view</a>

        <a href="/forms" id="daysOff" class="list-group-item list-group-item-action bg-light">Holidays</a>
        <c:if test="${sessionScope.levelOfAccess==2 || sessionScope.levelOfAccess==3}">
            <a href="/forms" id="user" class="list-group-item list-group-item-action bg-light">Users</a>
            <c:if test="${sessionScope.levelOfAccess==3}">
                <a href="/forms" id="managment" class="list-group-item list-group-item-action bg-light">Management</a>
                <a href="/forms" id="teams" class="list-group-item list-group-item-action bg-light">Teams</a>
            </c:if>
        </c:if>
<%--        <%if (levelOfAccess2 != 1) {%>

        <%if (levelOfAccess2 != 2) {%>
        <%
                }
            }
        %>--%>

    </div>
</div>
<!-- /#sidebar-wrapper -->
