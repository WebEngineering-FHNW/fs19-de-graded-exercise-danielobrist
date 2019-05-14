package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)

class GameController {
    static scaffold = Game

    def save() {
        def game = new Game(params)
        def winner = params.winner

        Team winnerTeam = Team.findByTeamName(winner)

        def wins = Team.findByTeamName(winnerTeam).wins
        winnerTeam.setWins(wins+1)
        game.save(flush: true)
        winnerTeam.save(flush: true)
        redirect(controller:"home", action: "index")
    }
}

