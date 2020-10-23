<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int levelOfAccess2 = (int) request.getSession().getAttribute("levelOfAccess"); %>
<!-- Sidebar -->
<div class="bg-light border-right" id="sidebar-wrapper">
    <div class="sidebar-heading">
        <img src="/images/javalanche2.jpg" alt="logo" width="144" height="60"/>
    </div>
    <div class="list-group list-group-flush">
        <a href="/employees" id="employees" class="list-group-item list-group-item-action bg-light"><i class="fas fa-user-alt"></i> Employees</a>
        <a href="/main" id="main" class="list-group-item list-group-item-action bg-light">  <i class="far fa-calendar-alt"></i> Calendar view</a>

        <a href="/holidayForms" id="daysOff" class="list-group-item list-group-item-action bg-light">Holidays</a>
        <c:if test="${sessionScope.levelOfAccess==2 || sessionScope.levelOfAccess==3}">
            <a href="/teamForms" id="teams" class="list-group-item list-group-item-action bg-light">Teams</a>
            <c:if test="${sessionScope.levelOfAccess==3}">
                <a href="/userForms" id="user" class="list-group-item list-group-item-action bg-light">Users</a>
                <a href="/ManagementForms" id="managment" class="list-group-item list-group-item-action bg-light">Management</a>
            </c:if>
        </c:if>
    </div>
</div>

