@OLBREG
Feature: OLB

  Scenario Outline: Register a OLB user - Non English Characters
    Given user navigates to "<OLB_URL>"
    When user clicks on 'Register' button
    Then user fills the "<FIRSTNAME>","<LASTNAME>" and "EMAIL_ID" to create an user
    And user should be able to find the confirmation message
    When user is on the homepage after entering the "<USERNAME>" and password
    Then user finds a welcome alert message
    And user verifies the the "<USERNAME>"
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | FIRSTNAME | LASTNAME | USERNAME |
      | URL     | NONENG    | NONENG   | OLBUSER  |

  Scenario Outline: Register a OLB user
    Given user navigates to "<OLB_URL>"
    When user clicks on 'Register' button
    Then user fills the "<FIRSTNAME>","<LASTNAME>" and "EMAIL_ID" to create an user
    And user should be able to find the confirmation message
    When user is on the homepage after entering the "<USERNAME>" and password
    Then user finds a welcome alert message
    And user verifies the the "<USERNAME>"
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | FIRSTNAME | LASTNAME    | USERNAME |
      | URL     | Auto      | TestUserOLB | OLBUSER  |


  Scenario Outline: Adding a New book
    Given user navigates to "<OLB_URL>"
    When user is on the homepage after entering the "<USERNAME>" and password
    Then user finds a welcome alert message
    When user provides the valid activation code for "<OLB_URL>"
    Then user should be the find the new book added to the collections
    When user launches the book
    Then user should be able to switch pages
    And user closes the book
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | USERNAME |
      | URL     | OLBUSER  |

  Scenario Outline: Invalid Activation Codes
    Given user navigates to "<OLB_URL>"
    When user is on the homepage after entering the "<USERNAME>" and password
    Then user finds a welcome alert message
    Then user provides the invalid "Activation Code" to add the book and validates the appropriate error messgae
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | USERNAME |
      | URL     | OLBUSER  |

  Scenario Outline: Edit Profile - English Characters
    Given user navigates to "<OLB_URL>"
    When user is on the homepage after entering the "<USERNAME>" and password
    Then user finds a welcome alert message
    And user updates the firstname and lastname
    And user updates the username and password
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | USERNAME |
      | URL     | OLBUSER  |

  Scenario Outline: Edit Profile - Non English Characters
    Given user navigates to "<OLB_URL>"
    When user is on the homepage after entering the "<USERNAME>" and password
    Then user finds a welcome alert message
    And user updates the NE firstname and lastname
    And user updates the NE username and password
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | USERNAME |
      | URL     | OLBUSER  |

  Scenario Outline: Forgot Password
    Given user navigates to "<OLB_URL>"
    When user enters the "<USERNAME>" in the sign in page
    Then user clicks on 'Forgotten your username or password?' link to reset the password
    And user clicks on 'Continue' to generate the reset mail
    Then user verifies the confimation message
    And user check the mail for the reset link
    When user enters the New password after navigating to the change password page
    Then user validates the password change success message
    And user navigates to "<OLB_URL>"
    When user is on the homepage after entering the "<USERNAME>" and updated password
    Then user finds a welcome alert message
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | OLB_URL | USERNAME |
      | URL     | OLBUSER  |

  Scenario Outline: Legacy User
    Given user navigates to "<URL>"
    When user is on the EAC homepage after entering the "<USERNAME>" and "PASSWORD" for "<URL>" Environment
    Then user creates a Legacy user
    And user navigates to "<OLB_URL>"
    When user is on the homepage after entering the legacy "<USERNAME>" and password
    Then user provides a new Email address for the leagacy user
    And user finds a welcome alert message
    Then user clicks on 'Sign out' to terminate the session
    And user closes the browser

    Examples: 
      | URL | USERNAME | OLB_URL |
      | EAC | OLBUSER  | URL     |
