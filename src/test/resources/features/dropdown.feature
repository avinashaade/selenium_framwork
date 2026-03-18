Feature: Dropdown Testing

  Background:
    Given User opens the application

  Scenario Outline: Select different countries
    When User selects country "<country>"
    Then country should be selected as "<country>"

    Examples:
      | country |
      | India   |
      | Canada  |