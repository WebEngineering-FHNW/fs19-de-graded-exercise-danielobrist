<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <asset:stylesheet src="style.css"/>
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
<div id="firstrow" class="row">
    <div id="firstcolumn" class="column">
        <div class="card">
            <div class="container">
                <h1>Top 10</h1>
                <table id="topten" class="table-hover">
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
        </div>
    </div>

    <div id="secondcolumn" class="column">
        <div class="card">
            <div class="container">
                <h1>New Game</h1>
                <g:form class="form" controller="game" action="save">
                    <tmpl:upDownInput class="form-control" name="scoreHomeTeam" label="Home" value="0"/>
                    <tmpl:upDownInput class="form-control" name="scoreGuestTeam" label="Guest" value="0"/>
                    <label>Winner</label>
                    <g:select class="form-control" name="winner" from="${webec.Team.list()}"/>
                    <label>Loser</label>
                    <g:select class="form-control" name="loser" from="${webec.Team.list()}"/>
                    <div>
                        <g:hiddenField name="date"/>
                    </div>
                    <g:submitButton name="save" value="save"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>