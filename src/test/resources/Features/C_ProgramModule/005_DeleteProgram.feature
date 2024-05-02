@tag
Feature: Delete Program

  Background: Admin is on dashboard page after Login and clicks Program on the navigation bar
   # Given Admin is on dashboard page after Login
    When Admin clicks Program on the navigation bar
    Then Admin should see a heading with text Manage Program on the page

  @tag1
  Scenario: Delete Program
    When Admin clicks <Delete> button on the data table for any row
    Then Admin should see a alert open with heading Confirm along with  <YES> and <NO> button for deletion

  @tag2
  Scenario: Click Yes on deletion window
    When Admin clicks <Delete> button on the data table for any row
    And Admin clicks <Yes> button in the dialog box to confirm the deletion
    Then Admin gets a message Successful Program Deleted alert and able to see that program deleted in the data table

  @tag3
  Scenario Outline: Delete a specific Program
    When Admin find a specific program from "<sheet>" and clicks <Delete> button on the data table for the specific row <row>
    And Admin clicks <Yes> button in the dialog box to confirm the deletion
    Then Admin gets a message Successful Program Deleted alert and able to see that program deleted in the data table

    Examples: 
      | sheet  | row |
      | ProgramName |   0 |


  @tag4
  Scenario: Click No button  on deletion window
    When Admin clicks <Delete> button on the data table for any row
    And Admin clicks <NO> button on the alert
    Then Admin can see the deletion alert disappears without deleting

  @tag5
  Scenario: Validate Cancel/Close(X) icon on Confirm Deletion alert
    When Admin clicks <Delete> button on the data table for any row
    And Admin clicks Close icon on Confirm Deletion alert  on the alert
    Then Admin can see the deletion alert disappears without any changes

