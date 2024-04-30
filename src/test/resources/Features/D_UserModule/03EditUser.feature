Feature: Sort User Feature

  Background: 
    Given Admin is on dashboard page after Login and clicks User on the navigation bar
    

  @01UserPage_EditUser_verifyUserPopup
  Scenario Outline: Validate row level edit icon
  
  Given Admin is on Manage User Page
  When Admin clicks on the edit icon 
  Then A new pop up with User details appears from sheet "<option>" and "<sheetName>"
  
  Examples: 
      | option     | sheetName |
      | VerifyUserDetailsPopup | UserPage_Edit  |
     

 @02UserPage_EditUserFirstName_Valid
  Scenario Outline: Check if the fields are updated with valid data
  
  Given Admin is on Manage User Page
  When Update the fields with valid data and click submit from sheet "<option>" and "<sheetName>"
  Then Admin gets message "User updated Successfully " and see the updated values in data table
  
   Examples: 
      | option     | sheetName |
      | UpdateUserFirstName | UserPage_Edit  |
      | UpdateUserMiddleName | UserPage_Edit  |

   
