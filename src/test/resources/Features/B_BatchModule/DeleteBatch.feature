
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Validate row level delete icon
  Given The delete icon on row level in data table is enabled
  When Admin clicks the delete icon
  Then Alert appears with yes and No option
  
  Scenario: Validate accept alert
  Given Admin clicks the delete icon
  When Admin click "yes" option
  Then Batch deleted alert pops and batch is no more available in data table
  
  Scenario: Validate reject alert
  Given Admin clicks the delete icon
  When Admin click "no" option
  Then Batch is still listed in data table
 