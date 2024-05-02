@dashboardtagNegative
Feature: This feature covers all Dashboard scenarios

 
@invalidCredentials
  Scenario: Verify Invalid credentials
  
  	Given Admin gives the correct LMS portal URL
    When Admin enter invalid credentials  and clicks login button 
    Then Admin should see error message "Invalid username and password Please try again"  