<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Sidebar -->
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

