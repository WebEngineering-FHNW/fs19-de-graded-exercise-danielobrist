package webec

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class GameControllerSpec extends Specification implements ControllerUnitTest<GameController> {

    SecUser user
    SecUser daniel
    Membership membership1
    Membership membership2
    Team goodTeam
    Team badTeam
    Game game
    Date today = new Date().clearTime()

    def populateValidParams(params) {
        params["winner"] = goodTeam
        params["loser"]  = badTeam
        params["scoreWinner"] = 10
        params["scoreLoser"] = 2
        params["date"] = today
        params["confirmed"] = false
    }

    void "Test the save action with wrong scores"() {
        given:"user submits a higher score for the loser team"
        params["winner"] = goodTeam
        params["loser"]  = badTeam
        params["scoreWinner"] = 8
        params["scoreLoser"] = 9
        params["date"] = today
        params["confirmed"] = false
        controller.gameService = Mock(GameService) {
            1 * createGame(_, _, _, _)
        }

        when:"save is called for a game instance with wrong scores"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save()

        then:"A error is returned"
        response.redirectedUrl == '/home/index'
        flash.message != null
    }
}
