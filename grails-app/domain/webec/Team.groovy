package webec

class Team {

    String teamName
    int wins
    int losses

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
    }

}
