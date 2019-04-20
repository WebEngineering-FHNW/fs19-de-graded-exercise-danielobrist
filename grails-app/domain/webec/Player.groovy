package webec

class Player {

    String  nickname
    String firstName
    String lastName

    @Override
    String toString() {
        if (firstName != null && lastName != null) {
            return nickname + "(" + firstName + " " +  lastName + ")"
        } else {
            return nickname
        }
    }

    static constraints = {
        nickname    blank:      false
        firstName   nullable:   true
        lastName    nullable:   true
    }
}
