package webec

import grails.gorm.transactions.Transactional

@Transactional
class MembershipService {

    def addMembership(userid, team) {
        def targetTeam = Team.findByTeamName(team)
        def player = SecUser.findById(userid)
            // checks if the user is already in the selected team
            if(!targetTeam.members.player.contains(player)) {
                Membership membership = new Membership(player: player, team: targetTeam)
                membership.save(flush: true)
            } else {
                throw new RuntimeException("You are already a member of ${targetTeam}!")
            }
    }
}
