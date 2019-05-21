package webec

import grails.testing.gorm.DataTest
import spock.lang.Specification

class TeamSpec extends Specification implements DataTest{

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

    void "test saving team properties"() {

        when:
        Team anotherTeam = new Team(
                teamName: "Testteam",
                captain: user,
                wins: 100,
                losses: 0,
                winLossRatio: 100
        )

        then:
        assert anotherTeam.teamName == "Testteam"
        assert anotherTeam.captain == user
        assert anotherTeam.wins == 100
        assert anotherTeam.losses == 0
        assert anotherTeam.winLossRatio == 100
    }

    void "team is changed and added to game list after save is called"() {

        when:
        goodTeam.teamName = "SuperGoodTeam"
        goodTeam.save()

        then:
        List<Team> teams = Team.list()
        assert teams.size() == 2
        assert teams[0].teamName == "SuperGoodTeam"
        assert teams[1].teamName == "badTeam"
    }
}
