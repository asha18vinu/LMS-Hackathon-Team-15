Feature: Delete Multiple Batches
 
Background: 
  Given Admin login with valid credentials
  Given Admin is logged on the Lms portal dashboardPage after login
<<<<<<< HEAD
  When  Admin clicks "Batch" from navigation bar
=======
  When  Admin clicks Batch from navigation barr
>>>>>>> 827f3cef0c4a9a6baa5e6cfbc2f265fbce3f2a45
  
  
 @deleteMultiple
  Scenario: Validate the delete icon below the header 
    Given None of the checkboxes in data table are selected  
    Then  The delete icon under the "Manage Batch" header should be disabled
 
  @deleteMultiple
  Scenario: Check for single row delete
  Given One of the checkbox row is selected
  When Click delete icon below "Manage Batch" header
  Then The respective row in the data table is deleted
 
 
  @deleteMultiple
  Scenario: Check for multi row delete
  Given Two or more checkboxes row is selected
  When Click delete icon below "Manage Batch" header for mutiple rows
  Then The respective row in the data table is deleted
  Then Admin logout of the application
 
   @deleteMultiple
  Scenario: Check all the deleted rows are reflected after loging back in
  Given Admin is in the manage batch page 
  When  The respective row in the data table is deleted
 
  