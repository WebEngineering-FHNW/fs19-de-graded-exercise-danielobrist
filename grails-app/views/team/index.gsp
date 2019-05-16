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
                <h1>Join a team</h1>
                <g:form class="form" controller="membership" action="create">
                    <label>Join a Team</label>
                    <g:select class="form-control" name="targetTeam" from="${webec.Team.list()}"
                              noSelection="${["": 'Select team to join...']}" required="true"/>
                    <g:submitButton name="join" value="Join"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>