<#--<#include "header.ftlh">-->
<#--<#include "navbar.ftlh">-->
<#--<#include "sidebar.ftlh">-->

<!DOCTYPE HTML>
<html lang="pl">
<title>Kalendarz urlopów</title>
<head>
    <meta charset="utf-8">
</head>
<body>
<div>
<#--    Hello ${dayOffRepository.dayOffList.toString()}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
    Hello ${daysOff.dayOffList.get(1).toString()}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
</div>

</body>
</html>



<!--
<div class="main">
    <div class="container">
        <div style="text-align: center;">
            <div class="middle">
                <div id="login">

                   <p>
<#--                       Jeste� zalogowany jako ${username}-->
                   </p>
                    <p>
<#--                        Twoje has�o to: ${password}-->
                    </p>

                    <div class="clearfix"></div>

                </div>  end login
                <div class="logo">
                    <p>
                        Kalendarz urlop�w 1
                    </p>
                    <p>
                        jAvalanche
                    </p>
                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>