package webec

class Game {

    Team    winner
    Team    loser
    int     scoreWinner
    int     scoreLoser
    Date    date
    boolean confirmed

    static constraints = {
        scoreWinner   range:    0..10
        scoreLoser    range:    0..10
        winner        nullable: true, blank: true
        loser         nullable: true, blank: true
    }

    Team getWinner() {
        return winner
    }

    void setWinner(Team winner) {
        this.winner = winner
    }

}