Feature: Sort User Feature

  Background: 
    Given Admin is on dashboard page after Login and clicks User on the navigation bar
    

  @01UserPage_SortUser_ById
  Scenario: Admin should see User details Sorted by ID
  
  Given Admin is on Manage User Page
  When Admin clicks on ID sort icon
  Then Admin should see User details are sorted by id 

   
