package webec

class Game {

    Team    homeTeam
    Team    guestTeam
    int scoreHomeTeam
    int scoreGuestTeam
    Date date

    static constraints = {
        scoreHomeTeam     range:    0..10
        scoreGuestTeam    range:    0..10
    }
}
