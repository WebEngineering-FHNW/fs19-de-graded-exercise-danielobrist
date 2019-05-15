package webec

class Team {

    String teamName
    int wins
    int losses
    int winLossRatio

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
        winLossRatio      nullable: true
    }

    static mapping = {
            winLossRatio formula: 'wins - losses'
    }

}
