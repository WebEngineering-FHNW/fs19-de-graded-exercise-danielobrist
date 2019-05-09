package webec

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
@Transactional(readOnly = true)
class TeamController {
    static scaffold = Team

    // TODO: not working yet
    def showMyTeams() {
        def springSecurityService
        def user = springSecurityService.getPrincipal()
        println(user.toString())

        List teams = Team.findAllByCaptain(user)
        List<Team> myTeams = teams*.teamName
        render text: "Your teams: $myTeams"
        [myTeams:myTeams]
    }

    def getAll() {
        List<Team> teams = Team.list()
    }

}
