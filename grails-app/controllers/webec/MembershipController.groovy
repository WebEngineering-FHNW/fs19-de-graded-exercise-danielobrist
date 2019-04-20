package webec

import grails.plugin.springsecurity.annotation.Secured
import webec.SecRole

@Secured(SecRole.ADMIN)
class MembershipController {
    static scaffold = Membership

    def danisMemberships() {
        Player d = Player.findByFirstName("Daniel")
        List result = Membership.findAllByPlayer(d)
        List<Team> teams = result*.team
        render text: "Teams: $teams"
    }
}
