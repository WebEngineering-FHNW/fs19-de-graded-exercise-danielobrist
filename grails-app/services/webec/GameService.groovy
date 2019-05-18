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

        def gameToSave = new Game(winner: winnerTeam, loser: loserTeam, scoreHomeTeam: scoreWinner, scoreLoser: scoreLoser, date: new Date(), confirmed: false)
        gameToSave.save(flush: true)

        // TODO: put this after game confirmation
        teamService.addWin(winnerTeam)
        teamService.addLoss(loserTeam)
        winnerTeam.save(flush: true)
        loserTeam.save(flush: true)
    }

    List<Game> listUnconfirmedGamesOfUser(SecUser user) {
        List<Team> teamsOfUser = Membership.findAllByPlayer(user).team
        List<Game> gamesLostByUser = Game.findAllByLoserInListAndConfirmed(teamsOfUser, false)
        return gamesLostByUser
    }
}
