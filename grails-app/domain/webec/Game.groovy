package webec

class Game {

    Team    winner
    Team    loser
    int     scoreWinner
    int     scoreLoser
    Date    date

    static constraints = {
        scoreWinner   range:    0..10
        scoreLoser    range:    0..10
    }

    Team getWinner() {
        return winner
    }

    void setWinner(Team winner) {
        this.winner = winner
    }

}