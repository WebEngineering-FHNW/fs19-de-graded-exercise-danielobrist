package webec

class Team {

    String teamName
    int wins
    int losses
    int totalGoalsMade
    int totalGoalsGotten

    SecUser captain

    static hasMany = [members: Membership]

    @Override
    String toString() {
        return teamName
    }

    static constraints = {
        teamName          blank: false, unique: true
        wins              min: 0
        losses            min: 0
        totalGoalsMade    min: 0
        totalGoalsGotten  min: 0
    }
}
