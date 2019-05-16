<html>
<head>
    <meta name="layout" content="main"/>
    <title>
        Sign in
    </title>
    <asset:stylesheet src="application.css"/>
</head>
<body>
<div id="login">
    <div class="inner">
        <div class="fheader">Please sign in</div>
        <form action="/login/authenticate" method="POST" id="loginForm" class="cssform" autocomplete="off">
            <p>
                <label for="username">Username</label>
                <input type="text" class="text_" name="username" id="username" placeholder="Username"/>
            </p>

            <p>
                <label for="password">Password</label>
                <input type="password" class="text_" name="password" id="password" placeholder="Password"/>
            </p>

            <p id="remember_me_holder">
                <input type="checkbox" class="chk" name="remember-me" id="remember_me"/>
                <label for="remember_me">Remember me</label>
            </p>

            <p>
                <input type="submit" id="submit" value="Sign in"/>
            </p>
        </form>
    </div>
</div>

</body>
</html>
