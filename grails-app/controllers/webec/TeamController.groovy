package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN, SecRole.USER])
class TeamController {
    static scaffold = Team

    def springSecurityService

    // lists Teams where current user is member
    def index() {
        def user = springSecurityService.currentUser
        List<Membership> memberships = Membership.findAllByPlayer(user)
        [userMemberships:memberships]
    }

    // calculate win-loss-ratio
    def winLossRatio(Team team) {
        //TODO: calculate ratio via service and pass it to view
    }

}
