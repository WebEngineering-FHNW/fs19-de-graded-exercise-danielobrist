package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
class GameController {

    def teamService

    static scaffold = Game

    def save() {
        def game = new Game(params)
        def winner = params.winner
        def loser = params.loser

        Team winnerTeam = Team.findByTeamName(winner)
        Team loserTeam = Team.findByTeamName(loser)

        teamService.addWin(winnerTeam)
        teamService.addLoss(loserTeam)

        game.save(flush: true)
        winnerTeam.save(flush: true)
        loserTeam.save(flush: true)
        redirect(controller:"home", action: "index")
    }
}

