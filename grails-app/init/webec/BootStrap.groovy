package webec

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        // in production or test, this might already be in the DB
        SecRole adminRole = save(SecRole.findOrCreateWhere(authority: SecRole.ADMIN))
        if (Environment.current != Environment.DEVELOPMENT) { // guard clause
            return
        }

        SecUser administrator = save(new SecUser(username: 'admin', password: 'letmein'))
        SecUserSecRole.create(administrator, adminRole, true) //flush

        // plausibility check
        assert SecRole.count() == 1
        assert SecUser.count() == 1
        assert SecUserSecRole.count() == 1

        Player daniel = save(new Player(firstName: "Daniel", lastName: "Obrist", nickName: "fanta"))

        for (int i = 0; i < 10; i++) {
            save(new Player(firstName: "first" + i, lastName: "last" + i, nickName: "nickName" + i))
        }

        for (int i = 0; i < 10; i++) {
            save(new Team(teamName: "team" + i))
        }

    }

    static save(domainObject) {
        domainObject.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}