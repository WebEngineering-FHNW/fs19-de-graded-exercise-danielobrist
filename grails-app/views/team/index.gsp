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
        <a href="/membership/create">Join a Team</a>
    </div>
</div>
</body>
</html>