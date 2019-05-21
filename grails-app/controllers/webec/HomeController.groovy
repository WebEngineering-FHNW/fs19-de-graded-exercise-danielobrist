package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN])
class HomeController {

    TeamService teamService

    /**
     * Calls topTen method in teamService to list the best 10 teams
     *
     * @return list with 10 best teams
     */
    @Secured([SecRole.ADMIN, SecRole.USER])
    def index() {
        List<Team> topTenList = teamService.topTen()
        [topTen:topTenList]
    }
}
