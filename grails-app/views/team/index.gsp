<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <asset:stylesheet src="style.css"/>
    <title>Kicker Board</title>
</head>

<body>
<div id=firstrow class="row">
    <div id="firstcolumn" class="column">
        <div class="card">
            <div class="container">
                <h1>My Teams</h1>
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
        </div>
    </div>

    <div id="secondcolumn" class="column">
        <div class="card">
            <div class="container">
                <h1>New team</h1>
                <a href="/team/create">Create a Team</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>