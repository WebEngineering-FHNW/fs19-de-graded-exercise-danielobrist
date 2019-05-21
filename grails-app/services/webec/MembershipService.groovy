package webec

import grails.gorm.transactions.Transactional

@Transactional
class MembershipService {

    /**
     * Creates a new Membership in the db.
     *
     * @param user ID and team passed by controller
     */
    def addMembership(userid, team) {
        def targetTeam = Team.findByTeamName(team)
        def player = SecUser.findById(userid)
            if(targetTeam == null) {
                throw new RuntimeException("Please select a team.")
            }
            // check if the user is already in the selected team
            if(!targetTeam.members.player.contains(player)) {
                Membership membership = new Membership(player: player, team: targetTeam)
                membership.save(flush: true)
            } else {
                throw new RuntimeException("You are already a member of ${targetTeam}!")
            }
    }

    /**
     * Deletes a Membership from the db.
     *
     * @param user ID and team passed by controller
     */
    def removeMembership(team, userid) {
        def targetTeam = Team.findByTeamName(team)
        def player = SecUser.findById(userid)
        Membership toDelete = Membership.findByPlayerAndTeam(player, targetTeam)
        toDelete.delete(flush:true)
    }
}
