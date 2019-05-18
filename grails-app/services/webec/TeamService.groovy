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

    def createTeam(String teamName, SecUser captain) {

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

    // unused
    // determines if a user is the captain of a team
    def isCaptain(SecUser me, Team team) {
        SecUser captain = Team.findById(team.id).getCaptain()
        return captain.equals(me)
    }
}