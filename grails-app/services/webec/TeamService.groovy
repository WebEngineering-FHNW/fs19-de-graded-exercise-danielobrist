package webec

import grails.gorm.transactions.Transactional

@Transactional
class TeamService {

    /**
     * Lists the 10 teams with the best win/loss ratio, ordered descending.
     *
     * @return top 10 list of teams
     */
    List<Team> topTen() {
        return Team.list(max: 10, sort: "winLossRatio", order: "desc")
    }

    /**
     * Adds a win to the win property of a team.
     *
     * @param team passed by controller
     */
    def addWin(Team team) {
        if (team != null) {
            def winsWinner = Team.findByTeamName(team).wins
            team.setWins(winsWinner + 1)
        }
    }

    /**
     * Adds a loss to the losses property of a team.
     *
     * @param team passed by controller
     */
    def addLoss(Team team) {
        if (team != null) {
            def lossesLoser = Team.findByTeamName(team).losses
            team.setLosses(lossesLoser + 1)
        }
    }

    /**
     * Calculates win/loss ratio property of a team.
     *
     * @param team passed by controller
     */
    def calculateWinLossRatio(Team team) {
        if (team.losses != 0) {
            team.winLossRatio = (team.wins / team.losses) as Double
        } else {
            team.winLossRatio = team.wins

        }
    }

    /**
     * Creates a new team in the db.
     *
     * @param team and captain passed by controller
     */
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

    /**
     * Removes all the references of a team. This has to be done before we can delete it from the db.
     *
     * @param team passed by controller
     */
    def cleanUp(Team teamToDelete) {
        def gameWinsOfTeam = Game.findAllByWinner(teamToDelete)
        def gameLossesOfTeam = Game.findAllByLoser(teamToDelete)
        def memberships = Membership.findAllByTeam(teamToDelete)

        gameWinsOfTeam.each { game -> game.winner = null }
        gameLossesOfTeam.each { game -> game.loser = null }
        gameWinsOfTeam.each { game -> game.save(flush: true) }
        gameLossesOfTeam.each { game -> game.save(flush: true) }

        memberships.each { mem -> mem.delete(flush: true) }
    }

    /**
     * Unused.
     * Determines if a user is the captain of a team.
     *
     * @param team passed by controller, user
     */
    def isCaptain(SecUser me, Team team) {
        SecUser captain = Team.findById(team.id).getCaptain()
        return captain.equals(me)
    }
}