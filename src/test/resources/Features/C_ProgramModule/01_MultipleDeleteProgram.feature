
Feature: Multiple Delete Program
  
  Background: Admin is on dashboard page after Login and clicks Program on the navigation bar
	Given Admin is logged in with valid credentials and admin is on the manage program page
	

  Scenario: Validate Common Delete button enabled after clicking on any checkbox
    When Admin clicks any checkbox in the data table
    Then Admin should see common delete option enabled under header Manage Program
 
 
  Scenario: Validate multiple programs deletion by selecting Single checkbox
    Given Admin clicks delete button under header after selecting the check box in the data table
    #And Admin is on Confirm Deletion alert
    When Admin clicks <YES> button on the alert
    Then Admin should land on Manage Program page and can see the selected program is deleted from the data table
   
Scenario: Validate multiple program deletion by selecting Single checkbox
    Given Admin clicks delete button under header after selecting the check box in the data table
  #  And Admin is on Confirm Deletion alert
    When Admin clicks <NO> button on the alert
    Then Admin should land on Manage Program page and can see the selected program is not deleted from the data table
 
  Scenario: Validate multiple program deletion by selecting multiple check boxes
    Given Admin clicks delete button under header after selecting multiple checkboxes in the data table
   # And  Admin is on Confirm Deletion alert
    When Admin clicks <YES> button on the alert
    Then Admin should land on Manage Program page and can see the selected programs are deleted from the data table

  Scenario: Validate multiple program deletion by selecting multiple check boxes
    Given Admin clicks delete button under header after selecting multiple checkboxes in the data table
    #And Admin is on Confirm Deletion alert
    When Admin clicks <NO> button on the alert 
    Then Admin should land on Manage Program page and can see the selected programs are not deleted from the data table
 