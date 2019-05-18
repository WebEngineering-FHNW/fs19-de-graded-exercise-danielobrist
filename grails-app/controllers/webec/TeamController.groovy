package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN, SecRole.USER])
class TeamController {
    static scaffold = Team

    def springSecurityService
    def teamService

    // lists Teams where current user is member
    def index() {
        def user = springSecurityService.currentUser
        List<Membership> memberships = Membership.findAllByPlayer(user)
        [userMemberships:memberships]
    }

    def create() {
        def user = springSecurityService.currentUser
        String teamName = params.teamName
        SecUser captain = user
        try {
            teamService.createTeam(teamName, captain)
            flash.message = "Successfully created team ${teamName}"
        } catch (RuntimeException re) {
            println re.message
            flash.error = re.message
        }
        redirect(controller:"team", action: "index")
    }

    // calculate win-loss-ratio
    def winLossRatio(Team team) {
        //TODO: calculate ratio via service and pass it to view
    }

}
