package webec

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
@Transactional(readOnly = true)
class TeamController {
    static scaffold = Team

    def springSecurityService

    // lists Teams where current user is member
    def index() {
        def user = springSecurityService.currentUser
        List<Membership> memberships = Membership.findAllByPlayer(user)
        [userMemberships:memberships]
    }
}
