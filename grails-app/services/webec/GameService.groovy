package webec

import grails.gorm.transactions.Transactional

@Transactional
class GameService {

    def teamService

    Team createGame(String winnerName, String loserName, int scoreWinner, int scoreLoser) {

        Team winnerTeam = Team.findByTeamName(winnerName)
        Team loserTeam = Team.findByTeamName(loserName)

        if (winnerTeam == loserTeam) {
            throw new RuntimeException("You can't play table football against yourself!")
        }

        def gameToSave = new Game(winner: winnerTeam, loser: loserTeam, scoreHomeTeam: scoreWinner, scoreLoser: scoreLoser, date:new Date())
        gameToSave.save(flush: true)

        teamService.addWin(winnerTeam)
        teamService.addLoss(loserTeam)
        winnerTeam.save(flush: true)
        loserTeam.save(flush: true)
    }
}
