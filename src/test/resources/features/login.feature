Feature: Login to the application

  Scenario: Login with valid credentials
    Given I am on the login page
    When I enter valid username and password
    Then I should be redirected to the dashboard

