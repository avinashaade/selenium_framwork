Feature: Alerts Handling

  Scenario: Simple Alert Test
    Given User launches the browser
    When User clicks on "Simple Alert" button
    Then Alert should be displayed
    And User should see alert message "I am an alert box!"
    When User accepts the alert

  Scenario: Confirmation Alert Accept
    Given User launches the browser
    When User clicks on "Confirmation Alert" button
    When User accepts the alert
    Then Result message should be "You pressed OK!"

  Scenario: Confirmation Alert Dismiss
    Given User launches the browser
    When User clicks on "Confirmation Alert" button
    When User dismisses the alert
    Then Result message should be "You pressed Cancel!"

  Scenario: Prompt Alert Test
    Given User launches the browser
    When User clicks on "Prompt Alert" button
    When User enters "Avinash" in the prompt
    And User accepts the alert
    Then Result message should contain "Avinash"