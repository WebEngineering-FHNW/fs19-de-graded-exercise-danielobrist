package webec

import grails.gorm.transactions.Transactional

@Transactional
class TeamService {

    def membershipService
    def springSecurityService

    def addWin(Team team) {
        def winsWinner = Team.findByTeamName(team).wins
        team.setWins(winsWinner+1)
    }

    def addLoss(Team team) {
        def lossesLoser = Team.findByTeamName(team).losses
        team.setLosses(lossesLoser+1)
    }

    Team createTeam(String teamName, SecUser captain) {
        def springSecurityService

        boolean exists = Team.findAllByTeamName(teamName).teamName.contains(teamName)

        if (exists) {
            throw new RuntimeException("The name ${teamName} is already taken!")
        }
        if (teamName == "") {
            throw new RuntimeException('Please enter a valid name.')
        }
        def teamToSave = new Team(teamName: teamName, wins: 0, losses: 0, captain: captain)
            teamToSave.save(flush: true)
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