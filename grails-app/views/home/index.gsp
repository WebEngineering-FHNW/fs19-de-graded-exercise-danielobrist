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
            input.value = parseInt(input.value) + 1 ;
        }

        function decrease(valueName) {
            var input = document.getElementById(valueName);
            input.value = parseInt(input.value) - 1 ;
        }
    </script>
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
    <g:form controller="game" action="create">
        <tmpl:upDownInput name="scoreHomeTeam" label="Home" value="0"/>
        <tmpl:upDownInput name="scoreGuestTeam" label="Guest" value="0"/>
        <label>Home Team</label>
        <g:select name="homeTeam.teamName" from="${webec.Team.list()}"/>
        <label>Guest Team</label>
        <g:select name="guestTeam.teamName" from="${webec.Team.list()}"/>
        <g:submitButton name="save" value="save"/>
</g:form>

</div>
</div>
</body>
</html>