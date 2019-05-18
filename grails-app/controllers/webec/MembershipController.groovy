package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
class MembershipController {
    static scaffold = Membership
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService
    def membershipService

    @Secured([SecRole.ADMIN, SecRole.USER])
    def create() {
        Long userid = springSecurityService.getCurrentUser().id
        String teamName = params.targetTeam
        try {
            membershipService.addMembership(userid, teamName)
            flash.message = "Successfully joined team!"
        } catch (RuntimeException re) {
            println re.message
            flash.error = re.message
        }
        redirect(controller:"team", action: "index")
    }

}
