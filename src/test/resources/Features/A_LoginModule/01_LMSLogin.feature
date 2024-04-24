
@AdminLoginwithValidData
Feature: Admin login to the application with valid data



  @Smoke
  Scenario: Successfull login with valid credentials
    Given Admin is on the Login page
    When Admin enters the valid username and password   
    Then I validate the outcomes
 

  @tag2
  Scenario: Successful logout
    Given Admin Logout of the application
    Then Message displayed successfull

   