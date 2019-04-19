package webec

class Player {

    String firstName
    String lastName

    @Override
    String toString() {
        return firstName + " " +  lastName
    }

    static constraints = {
        firstName blank:    false
        lastName  nullable: true
    }
}
