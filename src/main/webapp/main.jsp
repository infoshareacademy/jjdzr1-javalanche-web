<%@include file="template/header.jsp"%>


        <%@ include file="features/calendarView/calendar.jsp" %>

        <%@ include file="features/calendarView/placeHolidayRequestDay.jsp" %>

        <%@ include file="features/employeeView/popupUser.jsp" %>

<script>
        $(document).ready(function () {
                $('#calendarTable').DataTable({
                        "dom":'<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 row justify-content-end"f>><"row"<"col-sm-12"tr>><"row"<"col-sm-12 col-md-5"i><"col-sm-12 col-md-7"p>>'
                });
                $('#calendarTable_filter label').addClass('justify-content-sm-end');
        });
</script>
<%@include file="template/footer.jsp"%>