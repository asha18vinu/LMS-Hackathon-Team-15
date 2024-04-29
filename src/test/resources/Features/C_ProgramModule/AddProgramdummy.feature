@tag
Feature: Add New Program Dummy

  Background: Admin is on dashboard page after Login and clicks Program on the navigation bar
    Given Admin is on dashboard page after Login
    When Admin clicks Program on the navigation bar
    Then Admin should see a heading with text Manage Program on the page

@tag3
  Scenario Outline: Delete a specific Program
    When Admin find a specific program from "<sheet>" and clicks <Delete> button on the data table for the specific row <row>
    And Admin clicks <Yes> button in the dialog box to confirm the deletion
    Then Admin gets a message Successful Program Deleted alert and able to see that program deleted in the data table

    Examples: 
      | sheet  | row |
      | sheet1 |   0 |