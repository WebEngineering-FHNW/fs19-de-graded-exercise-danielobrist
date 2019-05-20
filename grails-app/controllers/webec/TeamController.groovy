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
        List<Team> teamsCaptainOf = Team.findAllByCaptain(user)
        [userMemberships:memberships, teamsCaptainOf:teamsCaptainOf]
    }

    def create() {
        def user = springSecurityService.currentUser
        String teamName = params.teamName
        SecUser captain = user
        try {
            teamService.createTeam(teamName, captain)
            flash.message = "Successfully created team «${teamName}»"
        } catch (RuntimeException re) {
            flash.error = re.message
        }
        redirect(controller:"team", action: "manage")
    }

    def delete () {
        def teamToDelete = Team.findByTeamName(params.teamName)
        try{
            teamToDelete.delete(flush: true)
            flash.message= "Successfully deleted team «${teamToDelete}»"
        } catch (RuntimeException re) {
            flash.error = re.message
        }
        redirect(controller:"team", action: "manage")
    }

    // calculate win-loss-ratio
    def winLossRatio() {
        //TODO: calculate ratio via service and pass it to view
    }

}
