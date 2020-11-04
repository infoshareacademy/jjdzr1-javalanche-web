<%@ page import="com.infoshareacademy.DTO.DayOffDto" %>
<%@ page import="java.util.List" %>
<%@include file="template/header.jsp"%>



        <%@include file="features/validator.jsp"%>

        <% UserDto user = (UserDto) request.getAttribute("user");%>
        <% List<DayOffDto> pendingHolidayRequests = (List<DayOffDto>) request.getAttribute("pendingHolidayRequests");%>

        <% if (pendingHolidayRequests.isEmpty()){ %>
        <%@include file="features/holidaysView/noHolidaysDisplay.jsp"%>
        <% } else { %>
        <%@include file="features/holidaysView/holidaysDisplay.jsp"%>
        <% } %>

<script>
    $(document).ready(function () {
        $('#holidayRequestsTable').DataTable({
            "dom":'<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 row justify-content-end"f>><"row"<"col-sm-12"tr>><"row"<"col-sm-12 col-md-5"i><"col-sm-12 col-md-7"p>>'
        });
        $('#holidayRequestsTable_filter label').addClass('justify-content-sm-end');
    });
</script>
<%@include file="template/footer.jsp"%>
