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

        SecUser user = save(new SecUser(username: 'Daniel', password: 'password'))
        SecUserSecRole.create(user, userRole, true)

        // generate a few test users
        for (int i = 0; i < 10; i++) {
            String usr = "first" + i
            String pwd = "password" + i

            SecUser testuser = save(new SecUser(username: usr, password: pwd))
            SecUserSecRole.create(testuser, userRole, true)
        }

        // generate a few test teams
        for (int i = 0; i < 10; i++) {
            save(new Team(teamName: "team" + i))
        }

        // generate a few test games
        for (int i = 0; i < 10; i++) {
            save(new Game(playerOneHome: SecUser.first(),
                          playerTwoHome: user,
                          playerOneGuest: SecUser.last(),
                          playerTwoGuest: administrator))
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