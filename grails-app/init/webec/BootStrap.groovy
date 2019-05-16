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
        SecUserSecRole.create(administrator, adminRole, true)

        SecUser daniel = save(new SecUser(username: 'Daniel', password: 'password'))
        SecUserSecRole.create(daniel, adminRole, true)

        SecUser dierk = save(new SecUser(username: 'Dierk', password: 'toobad'))
        SecUserSecRole.create(dierk, adminRole, true)

        // generate a few test users
        for (int i = 0; i < 10; i++) {
            String usr = "user" + i
            String pwd = "password" + i

            SecUser testuser = save(new SecUser(username: usr, password: pwd))
            SecUserSecRole.create(testuser, userRole, true)
        }

        // generate a few test teams
        for (int i = 0; i < 10; i++) {
            save(new Team(teamName: "team " + i, captain: daniel, wins: i))
        }

        Team daniTeam = save(new Team(teamName: "DaniTeam", captain: daniel))
        Team badTeam = save(new Team(teamName: "VeryBadTeam", captain:administrator))
        Team goodTeam = save(new Team(teamName: "VeryGoodTeam", captain: dierk))

        // generate a few test games
        for (int i = 0; i < 10; i++) {
            save(new Game(winner:  daniTeam,
                          loser:  badTeam,
                          scoreHomeTeam:  1,
                          scoreGuestTeam: 10,
                          date: new Date()
            ))
        }

        // generate a few memberships
        save(new Membership(player: daniel, team: daniTeam))
        save(new Membership(player: administrator, team: badTeam))
        save(new Membership(player: dierk, team: goodTeam))

        // plausibility check
        assert SecRole.count() == 2
        assert SecUser.count() == 13
        assert SecUserSecRole.count() == 13

    }

    static save(domainObject) {
        domainObject.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}