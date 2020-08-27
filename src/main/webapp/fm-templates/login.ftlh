<#include "header.ftlh">
<link rel="stylesheet" href="/css/index.css">
<div class="main">
    <div class="container">
        <div style="text-align: center;">
            <div class="middle">
                <div id="login">

                    <form action="/login" method="post">

                        <fieldset class="clearfix">

                            <p><span class="fa fa-user"></span><input type="text" name="username" Placeholder="e-mail"
                                                                      required></p>
                            <!-- JS because of IE support; better: placeholder="Username" -->
                            <p><span class="fa fa-lock"></span><input type="password" name="password"
                                                                      Placeholder="hasło" required>
                            </p> <!-- JS because of IE support; better: placeholder="Password" -->

                            <div>
                                <span style="width:48%; text-align:left;  display: inline-block;"><a class="small-text"
                                                                                                     href="#">Zapomniałeś hasła?
                                </a></span>
                                <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit"
                                                                                                          value="Zaloguj"></span>
                            </div>

                        </fieldset>
                        <div class="clearfix"></div>
                    </form>

                    <div class="clearfix"></div>

                </div> <!-- end login -->
                <div class="logo">
                        <img src="/images/javalanche2.jpg" alt="logo" width="360" height="150"/>
                    <p>Kalendarz urlopów</p>

                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>
