package webec

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class GameIntegrationSpec extends GebSpec {

    void "User not signed in is redirected to Sign in"() {
        when:
        go '/'
        then:
        title == "Sign in"

        when:
        go '/team/manage'
        then:
        title == "Sign in"

        when:
        go '/game/confirmations'
        then:
        title == "Sign in"

        when:
        go '/team/index'
        then:
        title == "Sign in"
    }
}
