package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
class GameController {

    def gameService
    def springSecurityService

    static scaffold = Game

    @Secured([SecRole.ADMIN, SecRole.USER])
    def save() {
        String winner = params.winner
        String loser = params.loser
        int scoreWinner = params.scoreWinner.toInteger()
        int scoreLoser =  params.scoreLoser.toInteger()

        try {
            gameService.createGame(winner, loser, scoreWinner, scoreLoser)
            if(scoreLoser == 0) {
                flash.message = "Game saved! Wow! ${loser} literally got destroyed by ${winner}!"
            } else {
                flash.message = "Game saved! ${winner} defeated ${loser} with a score of ${scoreWinner}:${scoreLoser}!"
            }
        } catch (RuntimeException re) {
            flash.error = re.message
        }
        redirect(controller:"home", action: "index")
    }

    // lists all games a team lost for confirmation by the losing team
    def confirmations() {
        def user = springSecurityService.currentUser
        List <Game> unconfirmedGamesLostByUser = gameService.listUnconfirmedGamesOfUser(user)
        [gamesToConfirm:unconfirmedGamesLostByUser]
    }

    //confirm selected game
    def confirm() {
        Long gameId = params.gameId as Long
        gameService.confirmGame(Long.valueOf(gameId))
        flash.message = "Game confirmed!"
        redirect(controller:"game", action: "confirmations")
    }

    //refuse selected game - deletes it from db (should probably rework this in the future)
    def refuse() {
        Long gameId = params.gameId as Long
        gameService.refuseGame(gameId)
        flash.message = "Game refused!"
        redirect(controller:"game", action: "confirmations")
    }
}

