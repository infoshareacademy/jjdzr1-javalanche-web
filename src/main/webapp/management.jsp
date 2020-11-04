<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="template/header.jsp"%>

        <% int levelOfAccess = (int) request.getAttribute("levelOfAccess"); %>
        <%@ include file="features/managmentForms/managmentsCollapse.jsp" %>


<%@include file="template/footer.jsp"%>