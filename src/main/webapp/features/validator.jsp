<div style="margin: 10px">
    <% try {
        if (request.getSession().getAttribute("success").equals(true) & !request.getSession().getAttribute("message").toString().isEmpty()) {%>
    <div class="alert alert-success" role="alert">
        <%=request.getSession().getAttribute("task")%>
        <%=" "%>
        <%=request.getSession().getAttribute("message")%>
    </div>
    <%
    } else if (request.getSession().getAttribute("success").equals(false) & !request.getSession().getAttribute("message").toString().isEmpty()) {%>
    <div class="alert alert-danger" role="alert">
        <%=request.getSession().getAttribute("task")%>
        <%=" "%>
        <%=request.getSession().getAttribute("message")%>
    </div>
    <%} } catch (Exception ignored){

    } finally {
        request.getSession().removeAttribute("task");
        request.getSession().removeAttribute("message");
        request.getSession().removeAttribute("message");
    }%>
</div>