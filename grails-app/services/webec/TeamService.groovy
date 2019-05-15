package webec

import grails.gorm.transactions.Transactional

@Transactional
class TeamService {

    def addWin(Team team) {
        def winsWinner = Team.findByTeamName(team).wins
        team.setWins(winsWinner+1)
    }

    def addLoss(Team team) {
        def lossesLoser = Team.findByTeamName(team).losses
        team.setLosses(lossesLoser+1)
    }

    //TODO: finish this
    Team createTeam(String teamName) {
        Team team = new Team()
        team.teamName = teamName
        team.wins = calculateWins(team)
        team.losses = calculateLosses(team)

        team.save()
        return team
    }

    // logic for generating number of wins depending on games played by the team
    private int calculateWins(Team team) {
        // TODO: logic for generating number of wins depending on games played by the team
        return Game.findAllByWinner(team).count()
    }

    // logic for generating number of wins depending on games played by the team
    private int calculateLosses(Team team) {
        // TODO: logic for generating number of losses depending on games played by the team
        return Game.findAllByLoser(team).count()
    }

    // unused
    // determines if a user is the captain of a team
    def isCaptain(SecUser me, Team team) {
        SecUser captain = Team.findById(team.id).getCaptain()
        return captain.equals(me)
    }
}