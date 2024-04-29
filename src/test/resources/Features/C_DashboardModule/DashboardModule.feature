@dashboardtag
Feature: This feature covers all Dashboard scenarios

  @login
  Scenario: Verify after login  admin lands on manage program as dashboard page
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button
    Then Admin should see manage program as header

  @responseTime
  Scenario: Verify the response time
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button
    Then Maximum navigation time in milliseconds, defaults to 30 seconds 
    
  @invalidCredentials
  Scenario: Verify Invalid credentials
    Given Admin is in Home Page
    When Admin enter invalid credentials  and clicks login button 
    Then Admin should see error message "Invalid username and password Please try again"  
    
  @pageHeader
  Scenario: Verify LMS title 
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then Admin should see LMS -Learning management system  as title
    
  @pageTitle
  Scenario: Verify  LMS title alignment
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then LMS title should be on the top left corner of page  
    
  @navigationBar
  Scenario: Validate navigation bar text
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then Admin should see correct spelling in navigation bar text 
    
  @programButttonPosition
  Scenario: Validate navigation bar order first Program
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then Admin should see "Program" in the 1st place    
    
  @batchButttonPosition
  Scenario: Validate navigation bar order second Batch
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then Admin should see "Batch" in the 2nd place 
    
  @userButttonPosition
  Scenario: Validate navigation bar order third User
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then Admin should see "User" in the 3rd place 
    
  @logoutButttonPosition
  Scenario: Validate navigation bar order fourth Logout button
    Given Admin is in Home Page
    When Admin enter valid credentials and clicks login button 
    Then Admin should see "Logout" in the 4th place       