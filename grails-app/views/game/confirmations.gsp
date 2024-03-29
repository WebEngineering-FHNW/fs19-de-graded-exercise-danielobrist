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
                <h1>Open Confirmations</h1>
                <g:if test="${gamesToConfirm.size() > 0}">
                    <table>
                        <thead>
                        <tr>
                            <th>Winner</th>
                            <th>Loser</th>
                            <th>Score</th>
                            <th>Confirm</th>
                            <th>Decline</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${gamesToConfirm}">
                            <tr>
                                <td>${it.winner}</td>
                                <td>${it.loser}</td>
                                <td>${it.scoreWinner}:${it.scoreLoser}</td>
                                <td><g:link controller="game" action="confirm"
                                            params="[gameId: it.id]"><span class="closebtn">&#10004;</span></g:link>
                                </td>
                                <td><g:link controller="game" action="refuse"
                                            params="[gameId: it.id]"><span class="closebtn"
                                                                           onclick="return confirm('Are you sure you want to refuse this match?')">&#10008;</span></g:link>
                                </td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>
                <g:if test="${gamesToConfirm.size() == 0}">No games to confirm right now, please come back later. Only the captain of a team can confirm games.</g:if>
            </div>
        </div>
    </div>

    <div id="secondcolumn" class="column">
    </div>
</div>
</body>
</html>