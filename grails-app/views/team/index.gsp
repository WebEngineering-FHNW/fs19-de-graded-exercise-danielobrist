<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <asset:stylesheet src="application.css"/>
    <title>Kicker Board</title>
</head>

<body>
<div id=firstrow class="row">
    <div class="column">
        My Teams
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Wins</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${userMemberships}">
                <tr>
                    <td>${it.team.teamName}</td>
                    <td>${it.team.wins}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="column">
        Join new Team here
        <g:form controller="team" action="save">
            <label>Team</label>
            <g:select name="team.teamName" from="${webec.Team.list()}"/>
            <g:submitButton name="Join" value="save"/>
        </g:form>
    </div>
</div>
</body>
</html>