@tag
Feature: Edit Program Details

  Background: Admin is on Manage Program Page after clicks Add a Program  button on the navigation bar
    Given Admin is on the Manage Program Page
    When Admin clicks <Edit> button on the data table for any row
    Then Admin should see a popup open for Program details to Edit

  @tag1
  Scenario Outline: Edit Program Name
    When Admin edits name column in "<sheet>" and <row>  and clicks save button
    Then Admin gets a message Successful Program Updated alert and able to see the updated "<sheet>" information in "<field>" and <row>

    Examples: 
      | sheet  | row | field |
      | ProgramPage |   0 | name  |

  @tag2
  Scenario Outline: Edit Program description
    When Admin edits the value of the  Program description in "<sheet>"  and  <row> column and clicks save button
    Then Admin gets a message Successful Program Updated alert and able to see the updated "<sheet>" information in "<field>" and <row>

    Examples: 
      | sheet  | row | field       |
      | ProgramPage |   0 | description |

  @tag3
  Scenario Outline: Validate invalid values on the text column
    When Admin enters only numbers or special char in "<name>" and "<desc>" column
    Then Admin gets a Syntax Error  alert

    Examples: 
      | name | desc |
      | 1234 | @!$% |
      | %%^^ | 4446 |

  @tag4
  Scenario: Change Program Status
    When Admin changes the Status of a program and clicks save button
    Then Admin gets a message Successful Program Updated alert

  @tag5
  Scenario: Validate Cancel button on Edit popup
    When Admin clicks <Cancel> button on edit popup
    Then Admin can see the Program details popup disappears and can see nothing changed for particular program
