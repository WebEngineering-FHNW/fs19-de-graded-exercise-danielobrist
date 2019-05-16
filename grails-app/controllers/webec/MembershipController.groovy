package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
class MembershipController {
    static scaffold = Membership
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService
    def membershipService


    def create() {
        def membership = new Membership(params)
        SecUser user = springSecurityService.getCurrentUser()
        Long userid = user.id
        String teamName = params.targetTeam
        System.out.println(teamName)
        System.out.println(user)
        System.out.println(userid)

        membershipService.addMembership(userid, teamName)

        membership.save(flush: true)
        redirect(controller:"team", action: "index")
    }

}
