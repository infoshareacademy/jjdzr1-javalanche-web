<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html lang="pl">
<head>
    <link rel="stylesheet" href="/css/index.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<div class="main">
    <div class="container">
        <div style="text-align: center;">
            <div class="middle">
                <div id="login">

                    <form action="/main" method="post">

                        <fieldset class="clearfix">

                            <p><span class="fa fa-user"></span><input type="text" name="username" Placeholder="Username" required></p>
                            <!-- JS because of IE support; better: placeholder="Username" -->
                            <p><span class="fa fa-lock"></span><input type="password" name="password" Placeholder="Password" required>
                            </p> <!-- JS because of IE support; better: placeholder="Password" -->

                            <div>
                                <span style="width:48%; text-align:left;  display: inline-block;"><a class="small-text"
                                                                                                     href="#">Forgot
                                password?</a></span>
                                <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit"
                                                                                                          value="Sign In"></span>
                            </div>

                        </fieldset>
                        <div class="clearfix"></div>
                    </form>

                    <div class="clearfix"></div>

                </div> <!-- end login -->
                <div class="logo">
                    <p>
                        Kalendarz urlopów
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
