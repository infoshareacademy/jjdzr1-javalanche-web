<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="container-fluid" style="overflow: auto">
        <br>
        <p>Holiday statistics</p>
        <table class="table table-striped" id="holidayRequestsTable" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th scope="row">All holidays</th>
                <th scope="row">2 weeks +</th>
                <th scope="row">Percentage</th>

            </tr>
            </thead>
            <tbody>
            <% int i = 1;%>

            <tr>
                <td>all holidays
                </td>
                <td>2 weeks
                </td>
                <td>percentage
                </td>

            </tr>

            </tbody>
            <tfoot>
            <tr>
                <th scope="row">#</th>
                <th scope="row">E-mail</th>
                <th scope="row">First name</th>
                <th scope="row">Last name</th>
                <th scope="row">First day</th>
                <th scope="row">Last name</th>
                <th scope="row">Action</th>
            </tr>
            </tfoot>
        </table>
    </div>
</div>


