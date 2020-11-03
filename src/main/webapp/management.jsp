<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="template/header.jsp"%>
<!-- MAIN CONTENT GOES HERE -->

        <% int levelOfAccess = (int) request.getAttribute("levelOfAccess"); %>
        <%@ include file="features/managmentForms/managmentsCollapse.jsp" %>


<!-- END OF MAIN CONTENT -->
<%@include file="template/footer.jsp"%>