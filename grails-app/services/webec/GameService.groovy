package webec

import grails.gorm.transactions.Transactional

@Transactional
class GameService {

    def teamService

    /**
     * Creates a new Game in the db.
     * Checks for valid inputs and throws appropriate exceptions.
     *
     * @param name of winner team, name of loser team, score of winner, score of loser
     * @return exception in case of invalid params, which is caught in the GameController
     */
    def createGame(String winnerName, String loserName, int scoreWinner, int scoreLoser) {

        Team winnerTeam = Team.findByTeamName(winnerName)
        Team loserTeam = Team.findByTeamName(loserName)

        if (winnerTeam == null) {
            throw new RuntimeException("Please select a winner.")
        }

        if (loserTeam == null) {
            throw new RuntimeException("Please select a loser.")
        }

        if (winnerTeam == loserTeam) {
            throw new RuntimeException("You can't play table football against yourself!")
        }

        if (scoreWinner <= scoreLoser) {
            throw new RuntimeException("Winner score has to be higher than loser score.")
        }

        def gameToSave = new Game(winner: winnerTeam, loser: loserTeam, scoreWinner: scoreWinner, scoreLoser: scoreLoser, date: new Date(), confirmed: false)
        gameToSave.save(flush: true)
    }

    /**
     * Lists all lost games of the current user where he is set as the captain and which are not confirmed yet.
     *
     * @param user passed by controller
     * @return list of all unconfirmed games lost by the user where he is captain of the loser team
     */
    // returns list of lost games where current user is captain
    List<Game> listUnconfirmedGamesOfUser(SecUser user) {
        List<Team> teamsCaptainOf = Team.findAllByCaptain(user)
        List<Game> gamesLostByUser = Game.findAllByLoserInListAndConfirmed(teamsCaptainOf, false)
        return gamesLostByUser
    }

    /**
     * Confirms a game by setting confirm property to true.
     * Calls TeamService to add a win to the winner teams win property.
     * Calls TeamService to add a loss to the loser teams losses property.
     *
     * @param game ID passed by controller
     */
    def confirmGame(Long gameId){
        def gameToConfirm = Game.findById(gameId)
        Team winnerTeam = Team.findByTeamName(gameToConfirm.winner)
        Team loserTeam = Team.findByTeamName(gameToConfirm.loser)

        gameToConfirm.confirmed = true

        gameToConfirm.save(flush: true)
        teamService.addWin(winnerTeam)
        teamService.addLoss(loserTeam)
        teamService.calculateWinLossRatio(winnerTeam)
        teamService.calculateWinLossRatio(loserTeam)

        winnerTeam.save(flush: true)
        loserTeam.save(flush: true)
    }

    /**
     * Deletes game if it is refused (probably not the best solution, but has to do for now)
     *
     * @param game ID passed by controller
     */
    def refuseGame(Long gameId) {
        def gameToConfirm = Game.findById(gameId)

        gameToConfirm.delete(flush: true)
    }
}
