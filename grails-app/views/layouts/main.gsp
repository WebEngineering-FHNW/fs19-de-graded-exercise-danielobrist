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
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header" id="controllers" role="navigation">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">
                <asset:image src="soccerball.svg" alt="Kicker-Board Logo"/>
            </a>
        </div>

        <div class="navbar-collapse collapse" aria-expanded="false">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="/game/index">Games</a></li>
                <li><a href="/team/">My Teams</a></li>
                <sec:ifLoggedIn>
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
                </sec:ifLoggedIn>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <g:pageProperty name="page.nav"/>
                <sec:ifLoggedIn>
                    User: <sec:username/>
                    <g:link controller="logout">Log out</g:link>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <g:link controller="login">Log in</g:link>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
    </div>
</div>

<g:layoutBody/>

<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
