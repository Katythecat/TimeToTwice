Feature: Test login functionality
  Scenario: validate admin login
    When user enters a valid email and password
    And clicks on Login button
    Then the user is logged in

