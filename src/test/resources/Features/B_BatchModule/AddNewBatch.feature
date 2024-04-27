Feature: Add new batch
As an admin
  I want to verify the existence of fields on the Batch details page
  So that I can add a new batch with the required information
			

Background:
	Given Admin login with valid credentials
  Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks "Batch" from navigation bar
  Then Admin clicks "+A new Batch" button
  Then A new pop up with Batch details appears  
  
 

 
  Scenario Outline: Admin verifies the fields exist in Batch details page popup        
    Then Admin Verifies the "<Fields>" existance and its "<FieldType>"  
    
     Examples: 
      | Fields                |FieldType|
      | NameField      			  |textbox  |
      | NumberofClassesField  |SpinnerTextBox  |
      | DescriptionField      |textbox  |
      | ProgramnameField      |textbox  |
      | ActiveField           |radioButton|
      | InactiveField         |radioButton|

 
Scenario Outline: Check if description is optional field
 When Admin fill in all the fields except description with valid values "<Sheetname>" and <Rowno>
 Then Admin clicks the save button
 Then The newly added batch should be present in the data table in Manage Batch page
 
 Examples:
 |Sheetname|Rowno|
 |AddBatch |   0 |
 

@validbatch 
Scenario Outline: Admin adds a new Batch with mandatory fields with valid data
    Given Admin is on the BatchDetails page
    When Admin fills out the mandatory fields "<sheetname>" and <RowNo> 
    Then Admin clicks the save button
    Then Admin should see the Successfull message

   Examples:
   | sheetname         | RowNo  |
   | AddBatch  	           |0 		  |	


Scenario Outline: Check if the batch details are added in data table
Given Admin added the batch 
Then The newly added batch should be present in the data table in Manage Batch page



Scenario Outline: Check for error messages for inval
id fields
When Admin enters any of the <Fields> with invalid values
Then Admin should get error message

Examples: 
      | Fields            |InvalidValues|ErrorMessage                                                |      
      | Name       			  |@%sdsd				|This field should start with an alphabet and min 2 character|
      | number of classes | A           |Number of classes is required.|
      | Description       |!            |This field should start with an alphabet and min 2 character.|
      | Program Name      |							|Program Name is required.|
      | Status            |							|Status is required.|
      
@smoke
Scenario Outline: Check for error messages for mandatory fields
When Any of the mandatory fields are blank "<sheetname>"
Then Admin should get error message

 Examples:
   | sheetname         | 
   | AddBatch  	       |
      
      
      
      
      
      
      
      
      
      
      