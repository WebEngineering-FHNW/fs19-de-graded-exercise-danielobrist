package webec

import grails.gorm.transactions.Transactional

@Transactional
class TeamService {

    Team createTeam(String teamName) {
        Team team = new Team()
        team.teamName = teamName
        team.wins = calculateWins(team)
        team.losses = calculateLosses(team)

        team.save()
        return team
    }

    // determine if current user is captain of a team
    def isCaptain(SecUser me, Team team) {
        SecUser captain = Team.findById(team.id).getCaptain()
        boolean isCaptain = captain.equals(me)
        return captain.equals(me)
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
}