package webec

import grails.plugin.springsecurity.annotation.Secured
import webec.SecRole

@Secured(SecRole.ADMIN)
class PlayerController {
    static scaffold = Player

    def showPlayerTeams() {
        Player player = Player.findByFirstName("Daniel")
        List<Team> teams = Team.findAll();
        render text:"alle Teams von Daniel" + teams.toString()
    }
}
