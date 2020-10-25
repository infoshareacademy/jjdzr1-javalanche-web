<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<title>Holiday calendar</title>
<head>
    <%@ include file="features/headSection.jsp" %>
</head>
<body>
<div class="d-flex" id="wrapper">
    <%@ include file="template/sidebar.jsp" %>

    <div id="page-content-wrapper" >
        <%@ include file="template/headerbar.jsp" %>

        <% UserDto teamLeader = (UserDto) request.getAttribute("teamLeader"); %>
        <% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>

        <% if (teamLeader.getTeam() == null || teamLeader.getTeam().getUserEmail().isEmpty()){ %>

        <script type="text/javascript">
            $(window).on('load',function(){
                $('#emptyListModal').modal('show');
            });
        </script>

        <div class="modal fade" id="emptyListModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>Attention</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><%=teamLeader.getFirstName()%> <%=teamLeader.getLastName()%></p>
                        <p>Your team has not assigned any employee</p>
                    </div>
                    <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <% } else { %>
        <%@include file="features/teamDisplay.jsp"%>
        <% } %>
    </div>

</div>
