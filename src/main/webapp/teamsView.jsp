<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.infoshareacademy.DTO.TeamDto" %>
<%@include file="template/header.jsp" %>

<!-- MAIN CONTENT GOES HERE -->
<% List<TeamDto> teams = (List<TeamDto>) request.getAttribute("teams");%>
<% UserDto admin = (UserDto) request.getAttribute("admin");%>
<% List<UserDto> teamLeaders = (List<UserDto>) request.getAttribute("teamLeaders");%>
<% List<UserDto> usersInTeam = (List<UserDto>) request.getAttribute("userWithTeam");%>


<%@include file="features/validator.jsp" %>

<% if (teams.isEmpty()) { %>
<%@include file="features/teamsView/noTeamsDisplay.jsp" %>
<% } else { %>
<%@include file="features/teamsView/teamsDisplay.jsp" %>
<% } %>
</div>

</div>
<script>
    $(document).ready(function () {
        $('#teamsTable').DataTable({
            "dom": '<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 row justify-content-end"f>><"row"<"col-sm-12"tr>><"row"<"col-sm-12 col-md-5"i><"col-sm-12 col-md-7"p>>'
        });
        $('#teamsTable_filter label').addClass('justify-content-sm-end');
    });
</script>

<!-- END OF MAIN CONTENT -->
<%@include file="template/footer.jsp" %>