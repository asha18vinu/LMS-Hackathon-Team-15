Feature: Edit batch



Background: 
  Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks "Batch" from navigation bar
  
  
  @edit
  Scenario: Validate row level edit icon
    Given The edit icon on row level in data table is enabled
    When Admin clicks the edit icon
    Then A new pop up with Batch details appears
  

  @tag2
  Scenario Outline: Check if the fields are updated
    Given Admin clicks the edit icon
    When Update the fields with valid values and click save "<sheetname>" and <rowno>
    Then The updated batch details should appear on the data table
    Examples:
    |sheetname|rowno|
    |AddBatch |0    |

 @tag2
  Scenario: Check if the update throws error with invalid valued
    Given Admin clicks the edit icon
    When Update the fields with invalid values and click save
    Then Error message should appear

 @tag2
  Scenario: Check if you get error message when mandatory fields are erased
    Given Admin clicks the edit icon
    When Erase data from mandatory field
    Then Error message should appear

 @tag2
  Scenario: Check if description field is optional in update
    Given Admin clicks the edit icon
    When Erase data from description field
    Then The updated batch details should appear on the data table



 