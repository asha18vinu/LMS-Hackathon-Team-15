Feature: Manage Program Validation

  Background: Logged on the LMS portal as Admin
    Given Admin is on Home Page
    When Admin login with valid credentials
    Then Admin should land on dashboard page
     

  @tag1
  Scenario: Validate landing in Program Page
    When Admin clicks Program on the navigation bar
    Then Admin should see URL with Manage Program

  @tag2
  Scenario: Validate the heading
    When Admin clicks Program on the navigation bar
    Then Admin should see a heading with text Manage Program on the page

  @tag3
  Scenario: Validate Add New Program
    When Admin clicks Program on the navigation bar
    Then Admin should see a +A New Program button on the program page above the data table

  @tag4
  Scenario: Verify Search bar on the Program page
    When Admin clicks Program on the navigation bar
    Then Admin should see Search bar with text as Search...

  @tag5
  Scenario: Validating the default state of Delete button
    When Admin clicks Program on the navigation bar
    Then Admin should see a Delete button on the top left hand side as Disabled

  @tag6
  Scenario: Validate that number of records (rows of data in the table) displayed
    When Admin clicks Program on the navigation bar
    Then Admin should see the number of records (rows of data in the table) displayed on the page.

  @tag7
  Scenario: Verify data table on the Program page
    When Admin clicks Program on the navigation bar
    Then Admin should see data table on the Manage Program Page with following column headers (Program Name, Program Description, Program Status, Edit,Delete)

  @tag8
  Scenario: Validate the text and pagination icon below the data table
    When Admin clicks Program on the navigation bar
    Then Admin should see the text as Showing x to y of z entries along with Pagination icon below the table.

  @tag9
  Scenario: Verify Sort arrow icon on the data table
    When Admin clicks Program on the navigation bar
    Then Admin should see the sort arrow icon beside to each column header except Edit and Delete

  @tag10
  Scenario: Verify Check box on the data table
    When Admin clicks Program on the navigation bar
    Then Admin should see check box on the left side in all rows of the data table

  @tag11
  Scenario: Verify Edit and Delete buttons
    When Admin clicks Program on the navigation bar
    Then Admin should see the Edit and Delete buttons on each row of the data table

  @tag12
  Scenario: Validate the footer
    Then Admin should see the footer as In total there are z programs.z- Total number of records
