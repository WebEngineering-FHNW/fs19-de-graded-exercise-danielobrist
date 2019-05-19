package webec

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

class GameServiceSpec extends HibernateSpec implements ServiceUnitTest<GameService>{

    List<Class> getDomainClasses() { [SecUser, Team, Game]}

    def "test listing of unconfirmed games with listUnconfirmedGamesOfUser service"() {
        when: 'Users, teams and games are stored in db'
        SecUser currentUser = new SecUser(username: 'user', password: 'pw').save()
        SecUser daniel = new SecUser(username: 'daniel', password: 'pw').save()

        Team team1 = new Team(teamName: "team1", captain: currentUser, wins: 100).save()
        Team team2 = new Team(teamName: "team2", captain: daniel, wins: 20).save()


        Game.saveAll(
                new Game(winner:team1,
                        loser: team2,
                        scoreWinner: 10,
                        scoreLoser: 1,
                        date: new Date(),
                        confirmed: false),

                new Game(winner:team1,
                        loser: team2,
                        scoreWinner: 10,
                        scoreLoser: 2,
                        date: new Date(),
                        confirmed: true),

                new Game(winner:team2,
                        loser: team1,
                        scoreWinner: 10,
                        scoreLoser: 3,
                        date: new Date(),
                        confirmed: false),

                new Game(winner:team2,
                         loser: team1,
                         scoreWinner: 10,
                         scoreLoser: 4,
                         date: new Date(),
                         confirmed: true)
        )

        then:
        Game.count() == 4

        when:'listUnconfirmedGamesOfUser is called'
        List<Game> games = service.listUnconfirmedGamesOfUser(currentUser)

        then: 'only unconfirmed games are found where the user is captain of loser team'
        games.size() == 1
        games[0].loser.teamName == 'team1'
        games[0].winner.teamName == 'team2'
        games[0].scoreLoser == 3
        games[1] == null
    }
}
