package webec

import grails.testing.gorm.DataTest
import spock.lang.Specification

class GameSpec extends Specification implements DataTest{

    SecUser user
    SecUser daniel
    Membership membership1
    Membership membership2
    Team goodTeam
    Team badTeam
    Game game

    def setup() {
        mockDomain SecUser
        mockDomain Team
        mockDomain Game
        mockDomain Membership

        user = new SecUser(username: 'user', password: 'pw').save()
        daniel = new SecUser(username: 'daniel', password: 'pw').save()

        goodTeam = new Team(teamName: "goodTeam", captain: user, wins: 100, losses: 0).save()
        badTeam = new Team(teamName: "badTeam", captain: daniel, wins: 13, losses: 13).save()

        membership1 = new Membership(player: user, team: goodTeam)
        membership2 = new Membership(player: daniel, team: badTeam)

        game = new Game(
                winner:goodTeam,
                loser: badTeam,
                scoreWinner: 10,
                scoreLoser: 1,
                date: new Date(),
                confirmed: false
        )

    }

    def cleanup() {
        user.delete()
        daniel.delete()
        goodTeam.delete()
        badTeam.delete()
        membership1.delete()
        membership2.delete()
        game.delete()
    }

    void "test saving game properties"() {

        when:
        Game anotherGame = new Game(
                winner:badTeam,
                loser: goodTeam,
                scoreWinner: 10,
                scoreLoser: 9,
                date: new Date(),
                confirmed: false
        )

        then:
        assert anotherGame.winner == badTeam
        assert anotherGame.loser == goodTeam
        assert anotherGame.scoreWinner == 10
        assert anotherGame.scoreLoser == 9
        assert anotherGame.confirmed == false
    }

    void "game is added to game list after save is called"() {

        when:
        game.save()

        then:
        List<Game> games = Game.list()
        assert games.size() == 1
        assert games[0].winner == goodTeam
        assert games[0].loser == badTeam
    }

    void "win is NOT added to winner team after save (this should only happen after the confirmation)"() {

        when:
        game.save()

        then:
        assert goodTeam.wins   == 100
        assert goodTeam.losses == 0
        assert badTeam.wins    == 13
        assert badTeam.losses  == 13
    }
}
