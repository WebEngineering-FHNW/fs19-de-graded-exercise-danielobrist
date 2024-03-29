<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <title>
        <g:layoutTitle default="Kicker-Board"/>
    </title>
    <asset:link rel="icon" href="favicon-soccerball.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>

</head>

<body>
<sec:ifLoggedIn>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header" role="navigation">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div class="navbar-collapse collapse" aria-expanded="false" role="navigation">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li><a href="/team/manage">Teams</a></li>
                    <li><a href="/game/confirmations">Confirmations</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li><div class="dropdown">
                            <button class="dropbtn">Controllers for debugging</button>
                            <div class="dropdown-content">
                                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                                    <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                                </g:each>
                            </div>
                        </div></li>
                    </sec:ifAllGranted>
                    <sec:ifLoggedIn>
                        <li id="greeting">Hello <sec:username/></li>
                        <li><g:link controller="logout">Log out</g:link></li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li><g:link controller="login">Log in</g:link></li>
                    </sec:ifNotLoggedIn>
                    <g:pageProperty name="page.nav"/>
                </ul>
            </div>
        </div>
    </div>
</sec:ifLoggedIn>
<g:if test="${flash.message}">
    <div class="alert-success">
        <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span>
        ${flash.message}
    </div>
</g:if>
<g:if test="${flash.error}">
    <div class="alert-danger">
        <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span>
        ${flash.error}
    </div>
</g:if>
<g:layoutBody/>
</body>
</html>
