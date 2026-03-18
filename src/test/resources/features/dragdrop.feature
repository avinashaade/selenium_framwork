Feature: Drag and Drop

  Background:
    Given User opens the application

  Scenario: Drag element and drop into target
    When User performs drag and drop
    Then Element should be dropped successfully