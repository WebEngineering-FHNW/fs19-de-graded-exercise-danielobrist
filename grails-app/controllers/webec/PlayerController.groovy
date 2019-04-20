package webec

import grails.plugin.springsecurity.annotation.Secured
import webec.SecRole

@Secured(SecRole.ADMIN)
class PlayerController {
    static scaffold = Player
}
