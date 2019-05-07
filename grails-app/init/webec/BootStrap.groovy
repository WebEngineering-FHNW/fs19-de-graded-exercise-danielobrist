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

        SecUser user = save(new SecUser(username: 'user', password: 'password'))
        SecUserSecRole.create(user, userRole, true)

        Player daniel = save(new Player(firstName: "Daniel", lastName: "Obrist", nickName: "fanta"))

        for (int i = 0; i < 10; i++) {
            save(new Player(firstName: "first" + i, lastName: "last" + i, nickName: "nickName" + i))
        }

        for (int i = 0; i < 10; i++) {
            save(new Team(teamName: "team" + i))
        }

        // plausibility check
        assert SecRole.count() == 2
        assert SecUser.count() == 2
        assert SecUserSecRole.count() == 2

    }

    static save(domainObject) {
        domainObject.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}