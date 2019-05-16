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
                <li><a href="/team/index">My Teams</a></li>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li><div class="dropdown">
                            <button class="dropbtn">All Controllers</button>

                            <div class="dropdown-content">
                                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                                    <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                                </g:each>
                            </div>
                        </div></li>
                    </sec:ifAllGranted>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <g:pageProperty name="page.nav"/>
                <sec:ifLoggedIn>
                    <li id="greeting"> Hello <sec:username/> </li>
                    <li><g:link controller="logout">Log out</g:link> </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li><g:link controller="login">Log in</g:link></li>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
    </div>
</div>
</sec:ifLoggedIn>
<g:layoutBody/>
</body>
</html>
