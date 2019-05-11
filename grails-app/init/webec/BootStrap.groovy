package webec

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        // in production or test, this might already be in the DB
        SecRole adminRole = save(SecRole.findOrCreateWhere(authority: SecRole.ADMIN))
        SecRole userRole = save(SecRole.findOrCreateWhere(authority: SecRole.USER))

        if (Environment.current != Environment.DEVELOPMENT) { // guard clause
            return
        }

        SecUser administrator = save(new SecUser(username: 'admin', password: 'password'))
        SecUserSecRole.create(administrator, adminRole, true) //flush

        SecUser daniel = save(new SecUser(username: 'Daniel', password: 'password'))
        SecUserSecRole.create(daniel, adminRole, true)

        // generate a few test users
        for (int i = 0; i < 10; i++) {
            String usr = "first" + i
            String pwd = "password" + i

            SecUser testuser = save(new SecUser(username: usr, password: pwd))
            SecUserSecRole.create(testuser, userRole, true)
        }

        // generate a few test teams
        for (int i = 0; i < 10; i++) {
            save(new Team(teamName: "team" + i, captain: administrator, wins: i))
        }

        save(new Team(teamName: "DaniTeam", captain: daniel))

        // generate a few test games
        for (int i = 0; i < 10; i++) {
            save(new Game(homeTeam:  Team.last(),
                          guestTeam:  Team.first(),
                          scoreHomeTeam:  1,
                          scoreGuestTeam: 10,
                          date: new Date()
            ))
        }

        // plausibility check
        // assert SecRole.count() == 2
        // assert SecUser.count() == 2
        // assert SecUserSecRole.count() == 2

    }

    static save(domainObject) {
        domainObject.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}