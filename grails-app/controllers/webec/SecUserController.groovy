package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)
class SecUserController {

    static scaffold = SecUser
}
