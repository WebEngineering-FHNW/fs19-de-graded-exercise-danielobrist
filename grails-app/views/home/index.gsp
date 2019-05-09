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
    Top 10
    <table>
        <tr>
            <th>Team</th>
            <th>Wins</th>
        </tr>
    <tr>
        <g:each in="${topTen}">
            <td>${it.teamName}</td>
            <td>${it.wins}</td>
            </tr>
        </g:each>
    </table>
</div>
<div class="column">
    Submit new Game here

</div>
</div>
</body>
</html>