Feature: Delete Multiple Batches
 
Background: 
  Given Admin is logged on dashboardPage after login
  When  Admin clicks "Batch" from navigation bar
  
  
  @tag1
  Scenario: Validate the delete icon below the header 
    Given None of the checkboxes in data table are selected  
    Then  The delete icon under the "Manage Batch" header should be disabled
 
  @tag2
  Scenario: Check for single row delete
  Given One of the checkbox/ row is selected
  When Click delete icon below "Manage Batch" header
  Then The respective row in the data table is deleted
  
  Scenario: Check for multi row delete
  Given Two or more checkboxes/row is selected
  When Click delete icon below "Manage Batch" header
  Then The respective row in the data table is deleted
  
  
  