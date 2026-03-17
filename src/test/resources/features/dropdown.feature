Feature: Dropdown Testing

  Scenario: Select country
    Given user opens website
    When user selects country "India"
    Then country should be selected as "India"