package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN, SecRole.USER])
class HomeController {

    TeamService teamService

    // get the ten teams with the best most points
    def index() {
        List<Team> topTenList = teamService.topTen()
        [topTen:topTenList]
    }
}
