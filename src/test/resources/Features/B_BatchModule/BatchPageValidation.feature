

@tag
Feature: Batch Page Validation
				As an Admin I want to verify the batch page UI
			  and its addBatch functionality


Background: 
  Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks "Batch" from navigation bar
 
  
  Scenario: Validate landing in Batch Page 
  Then  Admin should see the "manage batch" in the url 
  
  
  Scenario: validate header in the Batch page
   Then Admin should see the "Manage Batch" in the header
   
  
  Scenario: Validate pagination in the Batch Page
  Then Admin should see the pagination controls under the data table
  
  Scenario Outline: Validate data table headers in the Batch Page
  Then Admin Should see the data table with headers "<sheetName>" and <Rowno>
  Examples: 
      | sheetName           |Rowno|
      | AddBatch            |0    |
    
  
   
  Scenario: Validate Delete button in Batch Page  
  Then Admin should be able to see the "Delete" icon button that is disabled
 
  Scenario: Validate "+ A New batch" in Batch Page
  Then Admin should be able to see the "+ A New batch" button
   @Batch
  Scenario: Validate data rows
  Then Each row in the data table should have a checkbox 
  
  
  Scenario: Validate pop up for adding batch
  Then A new pop up with Batch details appears
   
 