<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.infoshareacademy.DTO.TeamDto" %>
<%@ page import="com.infoshareacademy.DTO.DayOffDto" %>
<%@include file="template/header.jsp" %>

<!-- MAIN CONTENT GOES HERE -->
<%--<%DayOffDto dayOffs = request.getAttribute("dayOffs");%>--%>
<% List<DayOffDto> dayOffDtos = (List<DayOffDto>) request.getAttribute("dayOffs");%>
<% List<TeamDto> teams = (List<TeamDto>) request.getAttribute("teams");%>
<% UserDto admin = (UserDto) request.getAttribute("admin");%>
<% List<UserDto> teamLeaders = (List<UserDto>) request.getAttribute("teamLeaders");%>
<% List<UserDto> usersInTeam = (List<UserDto>) request.getAttribute("userWithTeam");%>

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
                <td>2 weeks
                </td>
                <td>percentage
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<%@include file="template/footer.jsp" %>

