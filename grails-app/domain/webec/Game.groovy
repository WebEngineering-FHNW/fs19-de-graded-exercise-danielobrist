package webec

class Game {

    Team    winner
    Team    loser
    int scoreHomeTeam
    int scoreGuestTeam
    Date date

    static constraints = {
        scoreHomeTeam     (range:    0..10, validator: {value, object ->
            if (value > 10 || value < 0) return false })
        scoreGuestTeam    (range:    0..10, validator: {value, object ->
            if (value > 10 || value < 0) return false })
    }
}