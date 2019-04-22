package webec

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PlayerSpec extends Specification implements DomainUnitTest<Player> {

    def setup() {
    }

    def cleanup() {
    }

    void "test toString() formatting"() {
        when:
        Player p1 = new Player(nickName: "danio", firstName: "Daniel", lastName: "Obrist");

        then:
        p1.toString() == "danio (Daniel Obrist)"
    }
}
