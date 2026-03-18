Feature: Alerts Handling

  Background:
    Given User opens the application

  Scenario: Simple Alert Test
    When User clicks on "Simple Alert" button
    Then Alert should be displayed
    And User should see alert message "I am an alert box!"
    When User accepts the alert

  Scenario: Confirmation Alert Accept
    When User clicks on "Confirmation Alert" button
    And User accepts the alert
    Then Result message should be "You pressed OK!"

  Scenario: Confirmation Alert Dismiss
    When User clicks on "Confirmation Alert" button
    And User dismisses the alert
    Then Result message should be "You pressed Cancel!"

  Scenario: Prompt Alert Test
    When User clicks on "Prompt Alert" button
    And User enters "Avinash" in the prompt
    And User accepts the alert
    Then Result message should contain "Avinash"