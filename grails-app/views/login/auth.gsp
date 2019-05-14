<html>
<head>
    <meta name="layout" content="main"/>
    <title>
        Anmelden
    </title>

    <asset:stylesheet src="style.css"/>

</head>

<body>

<div id="login">
    <div class="inner">
        <div class="fheader">Bitte anmelden</div>
        <form action="/login/authenticate" method="POST" id="loginForm" class="cssform" autocomplete="off">
            <p>
                <label for="username">Benutzer</label>
                <input type="text" class="text_" name="username" id="username" placeholder="Username"/>
            </p>

            <p>
                <label for="password">Passwort</label>
                <input type="password" class="text_" name="password" id="password" placeholder="Password"/>
            </p>

            <p id="remember_me_holder">
                <input type="checkbox" class="chk" name="remember-me" id="remember_me"/>
                <label for="remember_me">Angemeldet bleiben</label>
            </p>

            <p>
                <input type="submit" id="submit" value="Anmelden"/>
            </p>
        </form>
    </div>
</div>

</body>
</html>
