<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.infoshareacademy.DTO.DayOffDto" %>
<%@include file="template/header.jsp" %>

<!-- MAIN CONTENT GOES HERE -->
<% List<DayOffDto> dayOffDtos = (List<DayOffDto>) request.getAttribute("dayOffs");%>
<% Integer longHolidays = (Integer) request.getAttribute("longHolidays");%>

<%@include file="features/validator.jsp" %>

<div class="container-fluid">
    <div class="container-fluid" style="overflow: auto">
        <br>
        <H3>Holiday statistics</H3><br>
        <table class="table table-striped" id="StatisticsTable" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th scope="row">All holidays</th>
                <th scope="row">Long holidays</th>
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
        </table>
    </div>
</div>
<%@include file="template/footer.jsp" %>

