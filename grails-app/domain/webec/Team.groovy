package webec

class Team {

    String teamName
    int wins
    int losses
    int goalsMade
    int goalsGotten

    static hasMany = [members: Membership]

    @Override
    String toString() {
        return teamName
    }

    static constraints = {
        teamName    blank:      false
    }
}
