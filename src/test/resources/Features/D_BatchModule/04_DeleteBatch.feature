

Feature: Admmin checks the delte functionalities in the applciation


  
Background: 
  Given Admin login with valid credentials
  Given Admin is logged on the Lms portal dashboardPage after login
  When  Admin clicks Batch from navigation barr
  
 
  Scenario: Validate row level delete icon
  Given The delete icon on row level in data table is enabled
  When Admin clicks the delete icon
  Then Alert appears with yes and No option
  

  Scenario: Validate reject alert
  Given The delete icon on row level in data table is enabled
  Given Admin clicks the delete icon
  When Admin click "no" NO option
  Then Batch is still listed in data table
  

 
  Scenario: Validate accept alert
   Given The delete icon on row level in data table is enabled
  Given Admin clicks the delete icon
  When Admin click "yes" option
  Then Batch deleted alert pops and batch is no more available in data table
  
  
 