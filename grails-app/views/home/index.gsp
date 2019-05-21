<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <meta charset="UTF-8">
    <title>Kicker Board</title>
    <script>
        function increase(valueName) {
            var input = document.getElementById(valueName);
            if (input.value < 10) {
                input.value = parseInt(input.value) + 1;
            }
        }

        function decrease(valueName) {
            var input = document.getElementById(valueName);
            if (input.value > 0) {
                input.value = parseInt(input.value) - 1;
            }
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
                        <th>W/L Ratio</th>
                    </tr>
                    <g:each in="${topTen}">
                        <tr>
                            <td>${it.teamName}</td>
                            <td><g:formatNumber number="${it.winLossRatio}" type="number" maxFractionDigits="3" /></td>
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
                    <label>Winner</label>
                    <g:select class="form-control" name="winner" from="${webec.Team.list()}"
                              noSelection="${['null': 'Select winner...']}"/>
                    <tmpl:upDownInput class="form-control" name="scoreWinner" label="Score" value="10" maxVal="10"/>
                    <label>Loser</label>
                    <g:select class="form-control" name="loser" from="${webec.Team.list()}"
                              noSelection="${['null': 'Select loser...']}"/>
                    <tmpl:upDownInput class="form-control" name="scoreLoser" label="Score" value="0" maxVal="9"/>

                    <g:hiddenField name="date"/>

                    <g:submitButton name="save" value="Submit"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>