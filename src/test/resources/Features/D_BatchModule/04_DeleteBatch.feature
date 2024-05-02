
@tag
Feature: Title of your feature
  I want to use this template for my feature file
  
  
Background: 
  Given Admin login with valid credentials
  Given Admin is logged on the Lms portal dashboardPage after login
<<<<<<< HEAD
  When  Admin clicks "Batch" from navigation bar
=======
  When  Admin clicks Batch from navigation barr
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
  
 # @delete
  Scenario: Validate row level delete icon
  Given The delete icon on row level in data table is enabled
  When Admin clicks the delete icon
  Then Alert appears with yes and No option
  
  @delete
  Scenario: Validate reject alert
  Given The delete icon on row level in data table is enabled
  Given Admin clicks the delete icon
  When Admin click "no" NO option
  Then Batch is still listed in data table
  
 @delete
  Scenario: Validate accept alert
   Given The delete icon on row level in data table is enabled
  Given Admin clicks the delete icon
  When Admin click "yes" option
  Then Batch deleted alert pops and batch is no more available in data table
  
  
 