package webec

import grails.gorm.transactions.Transactional

@Transactional
class TeamService {

    // determine if current user is captain of a team
    def isCaptain(SecUser me, Team team) {
        SecUser captain = Team.findById(team.id).getCaptain()
        boolean isCaptain = captain.equals(me)
        return captain.equals(me)
    }

}