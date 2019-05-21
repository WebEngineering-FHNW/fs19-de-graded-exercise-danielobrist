package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN])
class TeamController {
    static scaffold = Team

    def springSecurityService
    def teamService


    // lists Teams where current user is member
    @Secured([SecRole.ADMIN, SecRole.USER])
    def manage() {
        def user = springSecurityService.currentUser
        List<Membership> memberships = Membership.findAllByPlayer(user)
        List<Team> teamsCaptainOf = Team.findAllByCaptain(user)
        [userMemberships:memberships, teamsCaptainOf:teamsCaptainOf]
    }

    @Secured([SecRole.ADMIN, SecRole.USER])
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

    @Secured([SecRole.ADMIN, SecRole.USER])
    def delete () {
        def teamToDelete = Team.findByTeamName(params.teamName)
        try{
            teamService.cleanUp(teamToDelete)
            teamToDelete.delete(flush: true)
            flash.message= "Successfully deleted team «${teamToDelete}»"
        } catch (RuntimeException re) {
            flash.error = re.message
        }
        redirect(controller:"team", action: "manage")
    }
}
