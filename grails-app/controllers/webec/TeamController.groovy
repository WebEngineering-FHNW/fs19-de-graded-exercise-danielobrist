package webec

import grails.plugin.springsecurity.annotation.Secured
import webec.SecRole

@Secured(SecRole.ADMIN)
class TeamController {

    static scaffold = Team
}
