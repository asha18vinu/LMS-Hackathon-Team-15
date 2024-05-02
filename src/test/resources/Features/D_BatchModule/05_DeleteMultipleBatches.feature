
Feature: Delete Multiple Batches
 
Background: 
  Given Admin login with valid credentials
  Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks Batch from navigation barr
  
@smoke
Scenario Outline: Admin adds a new Batch with mandatory fields with valid data
    Given Admin is on the BatchDetails page
    Then Admin clicks "+A new Batch" button
    When Admin fills out the mandatory fields "<sheetname>" and <RowNo> 
    Then Admin clicks the save button
    Then Admin should see the Successfull message

   Examples:
   | sheetname         | RowNo  |
   | AddBatch  	       |  0 		|	
 @smoke
  Scenario: Validate the delete icon below the header 
    Given None of the checkboxes in data table are selected  
    Then  The delete icon under the "Manage Batch" header should be disabled
 
@smoke
  Scenario: Check for single row delete
  Given One of the checkbox row is selected
  When Click delete icon below "Manage Batch" header
  Then The respective row in the data table is deleted
 
 @smoke

  @deleteMultiple

  Scenario: Check for multi row delete
  Given Two or more checkboxes row is selected
  When Click delete icon below "Manage Batch" header for mutiple rows
  Then The respective row in the data table is deleted
  Then Admin logout of the application
 

@smoke
  Scenario: Check all the deleted rows are reflected after loging back in
  Given Admin is in the manage batch page 
  When  The respective row in the data table is deleted
 
