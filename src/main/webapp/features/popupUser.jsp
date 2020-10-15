<%@ page import="com.infoshareacademy.DTO.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper-kwiatkowski
  Date: 16.09.2020
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>

<% List<UserDto> users2 = (List<UserDto>) request.getAttribute("users");%>
<% for (int i = 0; i < users2.size(); i++) { %>
<div class="modal fade" id="modalUser<%=i%>" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <style>
        th {
            text-align:center;
        }
    </style>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <table class="table table-striped">
                <tbody>
                <tr>
                    <th colspan="2">Employee details</th>
                </tr>
                <tr>
                    <td>First name</td>
                    <td><%= users2.get(i).getFirstName() %></td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td><%= users2.get(i).getLastName() %></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><%= users2.get(i).getEmail() %></td>
                </tr>
                <tr>
                    <td>Team</td>
                    <td><%String userTeam;
                        try{ userTeam = users2.get(i).getTeam().getName();}
                        catch(NullPointerException e){
                            userTeam = "Team not specified";
                    }

                    %>
                    <%= userTeam %>
                    </td>
                </tr>
                <tr>
                    <td>Number of days off left</td>
                    <td><%= users2.get(i).getDaysOffLeft() %></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<% } %>
