package webec

import grails.gorm.transactions.Transactional

@Transactional
class MembershipService {

    def addMembership(userid, team) {
        def targetTeam = Team.findByTeamName(team)
        def player = SecUser.findById(userid)
        Membership membership = new Membership(player:player, team:targetTeam)
        membership.save(flush:true)
    }
}
