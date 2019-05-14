<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <asset:stylesheet src="application.css"/>
    <title>Kicker Board</title>
    <script>
        function increase(valueName) {
            var input = document.getElementById(valueName);
            input.value = parseInt(input.value) + 1;
        }

        function decrease(valueName) {
            var input = document.getElementById(valueName);
            input.value = parseInt(input.value) - 1;
        }
    </script>
</head>

<body>
<div id=firstrow class="row">
    <div class="column">
        Top 10
        <table>
            <tr>
                <th>Team</th>
                <th>Wins</th>
            </tr>
            <g:each in="${topTen}">
                <tr>
                    <td>${it.teamName}</td>
                    <td>${it.wins}</td>
                </tr>
            </g:each>
        </table>
    </div>

    <div class="column">
        <a href="/game/create">Submit a game</a>
        <g:form controller="game" action="save">
            <tmpl:upDownInput name="scoreHomeTeam" label="Home" value="0"/>
            <tmpl:upDownInput name="scoreGuestTeam" label="Guest" value="0"/>
            <label>Winner</label>
            <g:select name="winner" from="${webec.Team.list()}"/>
            <label>Loser</label>
            <g:select name="loser" from="${webec.Team.list()}"/>
            <div>
                <g:hiddenField name="date"/>
            </div>
            <g:submitButton name="save" value="save"/>
        </g:form>
    </div>
</div>
</body>
</html>