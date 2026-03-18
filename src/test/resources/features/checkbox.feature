Feature: Checkbox functionality

  Background:
    Given User opens the application

  Scenario: Select multiple days
    When User selects "Monday" and "Sunday" checkboxes
    Then "Monday" and "Sunday" checkboxes should be selected