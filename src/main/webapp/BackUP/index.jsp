<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html lang="pl">
    <head>

    </head>
    <style>
        td
        {
            height : 30px;
            width : 30px;
            cursor: pointer;
        }
    </style>

    <script>
        function clickHere(){
            var table = document.getElementById("myTable");
            var row ;
            var cell;
            for(var i=0;i<2;i++){
                row = table.insertRow(i);
                for(var j=0;j<2;j++){
                    cell = row.insertCell(j);
                    cell.addEventListener("click",function(){
                        alert("cell clicked");
                    });
                }
            }
        }
    </script>
    </head>

    <body onload="clickHere()">
    <table id = "myTable" border="1"></table>
    </body>
</html>
