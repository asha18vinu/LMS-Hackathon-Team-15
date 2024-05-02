@tag
Feature: Add a Program

  Background: Admin is on dashboard page after Login and clicks Program on the navigation bar
   # Given Admin is on dashboard page after Login
    When Admin clicks Program on the navigation bar
    Then Admin should see a heading with text Manage Program on the page

  @tag1
  Scenario: Validate Program Details Popup window
    When Admin clicks <A New Program> button
    Then Admin should see a popup open for Program details with empty form along with <SAVE> and <CANCEL> button and Close(X) Icon on the top right corner of the window

  @tag2
  Scenario: Validate input fields and their text boxes in Program details form
    When Admin clicks <A New Program> button
    Then Admin should see two input fields and their respective text boxes in the program details window

  @tag3
  Scenario: Validate radio button for Program Status
    When Admin clicks <A New Program> button
    Then Admin should see two radio button for Program Status


