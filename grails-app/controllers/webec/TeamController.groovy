package webec

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
@Transactional(readOnly = true)
class TeamController {
    static scaffold = Team

    def springSecurityService

    // lists Teams where current user is captain
    def index() {
        def user = springSecurityService.currentUser
        List<Team> teams = Team.findAllByCaptain(user)
        [myTeamsList:teams]
    }
}
