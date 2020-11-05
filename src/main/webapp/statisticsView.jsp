<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.infoshareacademy.DTO.TeamDto" %>
<%@ page import="com.infoshareacademy.DTO.DayOffDto" %>
<%@ page import="com.infoshareacademy.restapi.Request" %>
<%@include file="template/header.jsp" %>

<!-- MAIN CONTENT GOES HERE -->
<% List<DayOffDto> dayOffDtos = (List<DayOffDto>) request.getAttribute("dayOffs");%>
<% Integer longHolidays = (Integer) request.getAttribute("longHolidays");%>

<%@include file="features/validator.jsp" %>

<script>
    $(document).ready(function () {
        $('#teamsTable').DataTable({
            "dom": '<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 row justify-content-end"f>><"row"<"col-sm-12"tr>><"row"<"col-sm-12 col-md-5"i><"col-sm-12 col-md-7"p>>'
        });
        $('#teamsTable_filter label').addClass('justify-content-sm-end');
    });
</script>

<div class="container-fluid">
    <div class="container-fluid" style="overflow: auto">
        <br>
        <H3>Holiday statistics</H3>
        <table class="table table-striped" id="holidayRequestsTable" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th scope="row">All holidays</th>
                <th scope="row">2 weeks +</th>
                <th scope="row">Percentage</th>
            </tr>
            </thead>
            <tbody>
            <% int i = 0;%>
            <%i = dayOffDtos.size();%>
            <tr>
                <td><%=i%>
                </td>
                <c:if test="${requestScope.longHolidays>=0}">
                    <td><%=longHolidays%>
                    </td>
                </c:if>
                </td>
                <td>Statistics service is offline.<br>
                    Try again later...
                </td>
                </td>
                <c:if test="${requestScope.longHolidays>=0}">
                    <td><%=(longHolidays / i) * 100%> %
                    </td>
                </c:if>
                <td>Statistics service is offline.<br>
                    Try again later...
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th scope="row">All holidays</th>
                <th scope="row">2 weeks +</th>
                <th scope="row">Percentage</th>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<%@include file="template/footer.jsp" %>

