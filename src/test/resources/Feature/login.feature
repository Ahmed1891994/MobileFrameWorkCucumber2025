Feature: Login

  Background:
    Given User is in Catalog Page
    When User presses burger button
    And User presses on Log In in menu side

  Scenario: user login with valid data
    When user login with valid VALID_STANDARD credentials
    Then User is in Catalog Page
    When User presses burger button
    Then User can see Log Out on menu side

  Scenario: user login with valid data
    When user login with valid VALID_PREMIUM credentials
    Then User is in Catalog Page
    When User presses burger button
    Then User can see Log Out on menu side