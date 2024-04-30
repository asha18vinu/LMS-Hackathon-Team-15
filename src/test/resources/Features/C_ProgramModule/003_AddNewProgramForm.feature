@tag
Feature: Add New Program Form Validation

  Background: Admin is on Manage Program Page after clicks Add a Program button on the navigation bar
    Given Admin is on the Manage Program Page
    When Admin clicks <A New Program> button
    Then Admin should see a popup open for Program details with empty form along with <SAVE> and <CANCEL> button and Close(X) Icon on the top right corner of the window

  @tag1
  Scenario: Empty form submission
    When Admin clicks <Save> button without entering any data
    Then Admin gets a Error message alert

  @tag2
  Scenario Outline: Enter only Program Name
    When Admin enters only Program Name "<Name>" in text box and clicks Save button
    Then Admin gets a message alert Description is required

    Examples: 
      | Name |
      | Java |
      | c    |
      | c++  |

  @tag3
  Scenario Outline: Enter only Program Description
    When Admin enters only Program description "<Description>" in text box and clicks Save button
    Then Admin gets a message alert Name is required

    Examples: 
      | Description |
      | Language    |

  @tag4
  Scenario: Select Status only
    When Admin selects only Status and clicks Save button
    Then Admin gets a message alert Name and Description required

  @tag5
  Scenario Outline: Validate invalid values on the text column
    When Admin enters only numbers or special char in "<name>" and "<desc>" column
    Then Admin gets a Syntax Error  alert

    Examples: 
      | name | desc |
      | 2482 | 7860 |
      | @@$$ | !!%% |

  @tag6
  Scenario: Validate Cancel or Close icon on Program Details form
    When Admin clicks Cancel or Close icon on Program Details form
    Then Program Details popup window should be closed without saving

  @tag7
  Scenario: Validate Cancel button on Program Details form
    When Admin enter valid data and clicks Cancel button
    Then Admin can see the Program details popup disappears without creating any program

  @tag8
  Scenario Outline: Validate Save button on Program Details form
    When Enter all the required fields with valid values in "<sheetname>" and <rownum> and click Save button
    Then Admin gets a message Successful Program Created alert and able to see the new program added in the data table for "<sheetname>" and <rownum>

    Examples: 
      | sheetname | rownum |
      | ProgramPage    |      0 |
