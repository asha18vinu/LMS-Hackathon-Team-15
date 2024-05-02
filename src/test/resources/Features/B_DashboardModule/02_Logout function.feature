@dashboardtag
Feature: This feature covers all logout function scenarios


@logout
  Scenario: Verify Logout button function
    Given Admin is in dashboard page
    When Admin click Logout button on navigation bar
    Then Admin should land on login in page
