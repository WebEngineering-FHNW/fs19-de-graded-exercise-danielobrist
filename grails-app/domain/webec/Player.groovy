package webec

class Player {

    String nickName
    String firstName
    String lastName

    static hasMany = [membership: Membership]


    @Override
    String toString() {
        if (firstName != null && lastName != null) {
            return nickName + " (" + firstName + " " +  lastName + ")"
        } else {
            return nickName
        }
    }

    static constraints = {
        nickName    blank:      false
        firstName   nullable:   true
        lastName    nullable:   true
    }
}
