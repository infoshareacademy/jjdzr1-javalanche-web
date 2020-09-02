<#include "header.ftlh">
<#include "navbar.ftlh">
<#include "sidebar.ftlh">

<#--<!DOCTYPE HTML>-->
<#--<html lang="pl">-->
<#--<title>Kalendarz urlopów</title>-->
<#--<head>-->
<#--    <meta charset="utf-8">-->
<#--</head>-->
<#--<body>-->
<div style="position: absolute; left: 150px; top:100px;">
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email address</th>
            <th>Remaining days off</th>
        </tr>
        <#list users.usersList as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.daysOffLeft}</td>
            </tr>
        </#list>
    </table>
</div>

</body>
</html>