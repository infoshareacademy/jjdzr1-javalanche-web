<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>


<% List<UserDto> users = (List<UserDto>) request.getAttribute("users");%>
<% String ids = "";%>

<form action="/forms?deleteUser" method="post">
    <div class="col-md-6">
        <div class="form-group">
            <label for="exampleFormControlSelect1">Select user to remove:</label>
            <select class="form-control" id="exampleFormControlSelect1" name="selectedIdToDelete">
                <%for(UserDto user : users){%>
                <option value=<%=user.getId()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%></option>
                <%}%>
            </select>
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit">Submit</button>
        </div>
    </div>
</form>
