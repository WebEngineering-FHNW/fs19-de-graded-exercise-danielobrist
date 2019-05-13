<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <asset:stylesheet src="application.css"/>
    <title>Kicker Board</title>
</head>
<body>
<div id= firstrow class="row">
    <div class="column">
        My Teams
        <table>
            <tr>
                <th>Name</th>
                <th>Wins</th>
            </tr>
        <tr>
            <g:each in="${userMemberships}">
                <td>${it.team.teamName}</td>
                <td>${it.team.wins}</td>
                </tr>
            </g:each>
        </table>
    </div>
    <div class="column">
        Join new Team here
    </div>
</div>
</body>
</html>