package webec

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

class TeamServiceSpec extends HibernateSpec implements ServiceUnitTest<TeamService> {

    List<Class> getDomainClasses() { [Team] }

    void "Adding a win to winner team after a new game was submitted"() {
        given:'Users and teams are stored in db'
        SecUser currentUser = new SecUser(username: 'user', password: 'pw').save()
        SecUser daniel = new SecUser(username: 'daniel', password: 'pw').save()

        Team team1 = new Team(teamName: "team1", captain: currentUser, wins: 100, losses: 0).save()
        Team team2 = new Team(teamName: "team2", captain: daniel, wins: 20, losses: 10).save()

        when: 'addWin and addLoss is called'
        service.addWin(team1)
        service.addLoss(team2)

        then: 'the teams have the correct number of wins respective losses'
        team1.wins == 101
        team2.wins == 20
        team1.losses == 0
        team2.losses == 11
    }
}