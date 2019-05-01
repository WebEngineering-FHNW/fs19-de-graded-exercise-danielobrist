package webec

import grails.plugin.springsecurity.annotation.Secured
import webec.SecRole

@Secured(SecRole.ADMIN)
class MembershipController {
    static scaffold = Membership
}
