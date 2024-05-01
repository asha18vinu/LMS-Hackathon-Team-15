Feature: Edit batch



Background: 
  Given Admin login with valid credentials
  Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks "Batch" from navigation bar
  
  
 @edit
  Scenario: Validate row level edit icon
    Given The edit icon on row level in data table is enabled
    When Admin clicks the edit icon
    Then A new pop up with Batch details appears
  

 @edit1
  Scenario Outline: Check if the fields are updated
    Given Admin clicks the edit icon to edit
    When Update the fields with valid values and click save "<sheetname>" and <rowno>
    Then The updated batch details should appear on the data table
    Examples:
    |sheetname|rowno|
    |AddBatch |0    |

 @edit
  Scenario Outline: Check if the update throws error with invalid valued
    Given Admin clicks the edit icon
    When Update the fields with invalid values and click save "<sheetname>" and <rowno>
    Then Error message should appear
      Examples:
    |sheetname|rowno|
    |AddBatch |0    |

 @edit
  Scenario Outline: Check if you get error message when mandatory fields are erased
    Given Admin clicks the edit icon
    When Erase data from mandatory field "<sheetname>" and <rowno>
    Then Error message should appear
    
       Examples:
    |sheetname|rowno|
    |AddBatch |1    |
    
    @MandatoryfieldsEnabled
   Scenario: Check if you if the mandatory fields are enabled
    Given Admin clicks the edit icon
    When check mandatory fields are enabled
    Then all mandatory fields should be enabled


  Scenario: Check if description field is optional in update
    Given Admin clicks the edit icon
    When Erase data from description field
    Then The updated batch details should appear on the data table
    
 



 