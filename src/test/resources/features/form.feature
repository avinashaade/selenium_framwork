Feature: Form Testing

  Background:
    Given User opens the application

  Scenario Outline: Enter user details
    When User enters name "<name>"
    And User enters email "<email>"
    Then Name should be "<name>"
    And Email should be "<email>"

    Examples:
      | name    | email                |
      | Avinash | avinash@test.com     |
      | Rahul   | rahul@test.com       |