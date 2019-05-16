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
        } catch (RuntimeException re) {
            println re.message
            flash.message = re.message
            //TODO: return message to view somehow
        }
        redirect(controller:"team", action: "index")
    }

}
