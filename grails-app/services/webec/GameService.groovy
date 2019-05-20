package webec

import grails.gorm.transactions.Transactional

@Transactional
class GameService {

    def teamService

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

        def gameToSave = new Game(winner: winnerTeam, loser: loserTeam, scoreWinner: scoreWinner, scoreLoser: scoreLoser, date: new Date(), confirmed: false)
        gameToSave.save(flush: true)
    }

    // returns list of lost games where current user is captain
    List<Game> listUnconfirmedGamesOfUser(SecUser user) {
        List<Team> teamsCaptainOf = Team.findAllByCaptain(user)
        List<Game> gamesLostByUser = Game.findAllByLoserInListAndConfirmed(teamsCaptainOf, false)
        return gamesLostByUser
    }

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

    def refuseGame(Long gameId) {
        def gameToConfirm = Game.findById(gameId)

        gameToConfirm.delete(flush: true)
    }
}
