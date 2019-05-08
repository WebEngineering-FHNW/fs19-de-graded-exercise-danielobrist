package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)

class GameController {

    static scaffold = Game
}

