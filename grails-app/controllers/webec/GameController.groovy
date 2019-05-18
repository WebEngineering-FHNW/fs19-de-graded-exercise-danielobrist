package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
class GameController {

    def gameService

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
}

