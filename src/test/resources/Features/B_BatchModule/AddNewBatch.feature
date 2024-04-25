Feature: Add new batch

Background:
 Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks "Batch" from navigation bar
  Then Admin clicks "+A new Batch" button
  Then A new pop up with Batch details appears  
  
  
  Scenario Outline: Admin verifies the fields exist in Batch details page popup        
    Then Admin Verifies the fields 
    
     Examples: 
      | Fields            |
      | Name       			  |
      | number of classes |
      | Description       |
      | Program Name      |
      | Status            |
   
Scenario: Check if description is optional field
 When Admin fill in all the fields except description with valid values
 Then admin clicks save button
 Then The newly added batch should be present in the data table in Manage Batch page
  
Scenario: Check if the batch details are added in data table
When Admin Fill in all the fields with valid values and click save
Then The newly added batch should be present in the data table in Manage Batch page

Scenario: Check for error messages for invalid fields
When Admin enters any of the fields with invalid values
Then Admin should get error message

Scenario: Check for error messages for mandatory fields
When Any of the mandatory fields are blank
Then Admin should get error message



Scenario Outline: Admin adds a new Batch with mandatory fields with valid data
    Given Admin is on the BatchDetails page
    When Admin fills out the mandatory fields   
    Then Admin clicks the save button
    Then A new pop up with Batch details appears

   Examples:
   | sheetname         | RowNo            |
   | Name  	       |0 		  |	
   







