package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN])
class GameController {

    def gameService
    def springSecurityService

    static scaffold = Game

    /**
     * Saves a game by calling GameService and flashes error messages if it fails.
     * Redirects to home/index
     *
     * @param winner, loser, scoreWinner, scoreLoser
     * @return success or error message
     */
    @Secured([SecRole.ADMIN, SecRole.USER])
    def save() {
        try {
            String winner = params.winner
            String loser = params.loser
            int scoreWinner = params.scoreWinner.toInteger()
            int scoreLoser = params.scoreLoser.toInteger()

            gameService.createGame(winner, loser, scoreWinner, scoreLoser)
            if (scoreLoser == 0) {
                flash.message = "Game submitted for confirmation! Wow! ${loser} literally got destroyed by ${winner}!"
            } else {
                flash.message = "Game submitted for confirmation! ${winner} defeated ${loser} with a score of ${scoreWinner}:${scoreLoser}!"
            }
        } catch (RuntimeException re) {
            flash.error = re.message
        }
        redirect(controller: "home", action: "index")
    }

    /**
     * Calls GameService to list all unconfirmed Games of the currently logged in user
     *
     * @param the currently logged in user
     * @return list of unconfirmed games by the currently logged in user
     */
    @Secured([SecRole.ADMIN, SecRole.USER])
    def confirmations() {
        def user = springSecurityService.currentUser
        List<Game> unconfirmedGamesLostByUser = gameService.listUnconfirmedGamesOfUser(user)
        [gamesToConfirm: unconfirmedGamesLostByUser]
    }

    /**
     * Confirms a game by calling confirmGame method from GameService
     *
     * @param ID from the game in the respective table row
     * @return success or error message
     */
    @Secured([SecRole.ADMIN, SecRole.USER])
    def confirm() {
        try {
            Long gameId = params.gameId as Long
            gameService.confirmGame(Long.valueOf(gameId))
            flash.message = "Game confirmed!"
        } catch (RuntimeException re) {
            flash.error = re.message
        }
        redirect(controller: "game", action: "confirmations")
    }

    /**
     * Refuses a game by calling refuseGame method from GameService
     * - deletes the game from db (should probably rework this in the future)
     *
     * @param ID from the game in the respective table row
     * @return success message
     */
    @Secured([SecRole.ADMIN, SecRole.USER])
    def refuse() {
        Long gameId = params.gameId as Long
        gameService.refuseGame(gameId)
        flash.message = "Game refused!"
        redirect(controller: "game", action: "confirmations")
    }
}

