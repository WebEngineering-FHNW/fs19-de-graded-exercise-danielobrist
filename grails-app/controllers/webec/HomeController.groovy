package webec

import grails.plugin.springsecurity.annotation.Secured

@Secured([SecRole.ADMIN, SecRole.USER])
class HomeController {

    // get the ten teams with the best win/loss ratio
    def index() {
        List<Team> topTenList = Team.list(max: 10, sort: "wins", order: "desc")
        [topTen:topTenList]
    }
}
