<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <title>Kicker Board</title>
</head>

<body>
<div id="firstrow" class="row">
    <div id="firstcolumn" class="column">
        <div class="card">
            <div class="container">
                <h1>My Memberships</h1>
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Wins</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userMemberships}">
                        <tr>
                            <td>${it.team.teamName}</td>
                            <td>${it.team.wins}</td>
                            <td><g:link controller="membership" action="leaveTeam"
                                        params="[teamName: it.team.teamName]"><span class="closebtn"
                                                                                    onclick="return confirm('Are you sure you want to leave ${it.team.teamName}?')">&#10008;</span></g:link>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="card">
            <div class="container">
                <h1>Teams founded by <sec:username/></h1>
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Wins</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${teamsCaptainOf}">
                        <tr>
                            <td>${it.teamName}</td>
                            <td>${it.wins}</td>
                            <td><g:link controller="team" action="delete"
                                        params="[teamName: it.teamName]"><span class="closebtn"
                                                                               onclick="return confirm('Are you sure you want to delete ${it.teamName}?')">&#x1F5D1;</span></g:link>
                            </td>
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
                    <g:select class="form-control" name="targetTeam" from="${webec.Team.list()}"
                              noSelection="${['null': 'Select team to join...']}"/>
                    <g:submitButton name="join" value="Join"/>
                </g:form>
            </div>
        </div>

        <div class="card">
            <div class="container">
                <h1>Create a team</h1>
                <g:form class="form" controller="team" action="create">
                    <label>Team name</label>
                    <g:textField name="teamName"/>
                    <g:submitButton name="create" value="Create"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>