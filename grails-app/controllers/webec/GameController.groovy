package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured(SecRole.ADMIN)

class GameController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static scaffold = Game

}

