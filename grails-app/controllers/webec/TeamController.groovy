package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN])
class TeamController {
    static scaffold = Team

    def springSecurityService
    def teamService

    /**
     * Lists memberships of current user.
     * Lists teams of which current user is captain (founder) of.
     *
     * @param user ID from current user
     * @return list of memberships per user, list of teams the user is captain of
     */
    @Secured([SecRole.ADMIN, SecRole.USER])
    def manage() {
        def user = springSecurityService.currentUser
        List<Membership> memberships = Membership.findAllByPlayer(user)
        List<Team> teamsCaptainOf = Team.findAllByCaptain(user)
        [userMemberships:memberships, teamsCaptainOf:teamsCaptainOf]
    }

    /**
     * Calls teamService to create new Team.
     *
     * @param user ID from current user, team name
     * @return success of error message
     */
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

    /**
     * Calls TeamService to clean up all references and deletes a team
     *
     * @param team name of team to delete
     * @return success or error message
     */
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
