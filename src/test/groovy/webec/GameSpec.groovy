package webec

import grails.testing.gorm.DataTest
import spock.lang.Specification

class GameSpec extends Specification implements DataTest {


    static today = new Date().clearTime()


    def setup() {
        mockDomain Team
        mockDomain Game

        Team homeTeam = new Team(teamName: "homeTeam", wins: 0, losses: 0, captain: "Dani").save(flush:true)
        Team guestTeam = new Team(teamName: "guestTeam", wins: 0, losses: 0, captain: "Dani").save(flush:true)

    }

    def cleanup() {
        homeTeam.delete()
        guestTeam.delete()
        game.delete()
    }

    // TODO: fix this test
    void "Adding a win to winner team after a new game was submitted"() {
        given:


        when:
        game = new Game(winner:  homeTeam,
                loser:  guestTeam,
                scoreHomeTeam:  10,
                scoreLoser: 1,
                date: today).save(flush:true)

        then:
        assert homeTeam.wins == 1
        assert guestTeam.wins == 0
    }
}
