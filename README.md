# WebEngineering Module, Graded Exercise

## Commit Proposal

Matriculation Number: 10-727-104
Email               : daniel.obrist@students.fhnw.ch

Project idea short description: 
* Kicker-Board (working title). 
* is a web application to manage teams and results from table football matches. Users can register themselves and join
one or multiple teams. Users can also create new matches and challenge other teams. A match involves always at least
two teams. Results can be submitted by the involved teams after the match (and must be approved by their opponents
to be fully valid). The web application creates leaderboards and displays the weekly/monthly/alltime champions
(individual players/teams), calculated by win-loss--differenece/goal-difference/and other data.

## Project confirmation

Cool idea. Go for it.

Confirmed.
D. König

## Project delivery <to be filled by student>

How to start the project: (if other than `grailsw run-app`)

Credentials:

Regular access: Username: Dierk, Password: toobad

Admin access: Username: admin, Password: password

How to test the project:  (if other than `grailsw test-app`)

Hand-written, static HTML 
project description:      (if other than `index.html` in project root directory)

External contributions:

Other comments: 

I'm particular proud of:

- Building my first grails web application

- Writing my own actions and service methods instead of using scaffold

- Nice feedback through snackbar messages

- Deleting a team cleans up all the references it had in other domains

- Detailed documentation of controller and services

## Project grading 

index.html given and valid.
When using input fields it would improve usability/accessibility to have label elements along with them.

works with one small glitch:
- Bug: on confirmation page: "Cannot get property 'losses' on null object"

Functionality:
- spring security
- custom styled login
- bootstrapped data
- composed view plus navigation to specialized views
- in-page js to support interactivity (counting up/down)
- specialized feedback monolog
- 3 domain classes, one M:1, one 1:M relation
  with confirmation workflow
  you got hasMany working (even though I advised against using it)
- constraints

Engineering:
- excellent commit log (even though a bit tail-heavy towards the deadline)
- all tests running, unit tests (domain and controller) very nice, Integration test is anemic
- html is valid
- comments are excellent
overall top-notch code quality!

I award an extra point (until we hit the ceiling) for the extra effort that went into
carefully hand-crafted controllers and views.

Congratulations!
You created a fully functional, distributed, interactive, security-enabled, database-backed, web application
from scratch on your own in a rather tight time frame showing solid knowledge of WebMVC and engineering practices.
Many professional developers in the industry would have estimated more time for this feature set.
You can use this project as a work proof when applying for a job.

Grade: 6.0









