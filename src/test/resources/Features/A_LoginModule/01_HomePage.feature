Feature: Home Page Verification

  Background: 
    Given Admin launch the browser
    When Admin gives the correct LMS portal URL

  @01HomePage_ValidURL
  Scenario Outline: Verify admin is able to land on home page
    Then Admin should land on the home page with title from sheet "<option>" and "<sheetName>"

    Examples: 
      | option              | sheetName |
      | VerifyHomePageTitle | HomePage  |

  @02HomePage_InValidURL
  Scenario Outline: Verify admin is able to land on home page with invalid URL
    When Admin gives the invalid LMS portal URL from sheet "<option>" and "<sheetName>"
    Then Admin should recieve 404 page not found error

    Examples: 
      | option     | sheetName |
      | InvalidUrl | HomePage  |

  @03HomePage_InValidURLEndpoint
  Scenario Outline: Verify admin is able to land on home page with invalid URL endpoint
    When Admin gives the invalid Url endpoint from sheet "<option>" and "<sheetName>"
    Then Admin should recieve 404 page not found error

    Examples: 
      | option             | sheetName |
      | InvalidUrlEndpoint | HomePage  |

  @04HomePage_Brokenlink
  Scenario Outline: Verify for broken link
    Then HTTP response >= 400 then the link is broken from sheet "<option>" and "<sheetName>"

    Examples: 
      | option            | sheetName |
      | VerifyBrokenLinks | HomePage  |

  @05HomePage_SpellCheck
  Scenario Outline: Verify the text spelling in the page
    Then Admin should see correct spellings in all fields from sheet "<option>" and "<sheetName>"

    Examples: 
      | option         | sheetName |
      | VerifyPageText | HomePage  |

  @06HomePage_VerifyLogo
  Scenario: Verify the company logo
    Then Admin should see logo on the left  side

  @06HomePage_VerifyApplicationName
  Scenario Outline: Verify application name
    Then Admin should see  application name from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                | sheetName |
      | VerifyApplicationName | HomePage  |

  @07HomePage_VerifyCompanyName
  Scenario Outline: Verify company name
    Then Admin should see company name from sheet "<option>" and "<sheetName>" below the app name

    Examples: 
      | option            | sheetName |
      | VerifyCompanyName | HomePage  |

  @08HomePage_VerifySignInContent
  Scenario: Validate sign in content
    Then Admin should see Please login to LMS application

  @09HomePage_VerifyTotalTextField
  Scenario Outline: Verify text field is present from sheet "<option>" and "<sheetName>"
    Then Admin should see two text field from sheet "<option>" and "<sheetName>"

    Examples: 
      | option               | sheetName |
      | VerifyTotalTextField | HomePage  |

  @10HomePage_VerifyFirstTextField
  Scenario Outline: Verify text on the first text field
    Then Admin should in the first text field from sheet "<option>" and "<sheetName>"

    Examples: 
      | option               | sheetName |
      | VerifyFirstTextField | HomePage  |

  @11HomePage_VerifySymbol_FirstTextField
  Scenario Outline: Verify asterik next to user text
    Then Admin should see asterik symbol next to user text from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                       | sheetName |
      | VerifySymbolOfFirstTextField | HomePage  |

  @12HomePage_VerifySecondTextField
  Scenario Outline: Verify text on the second text field
    Then Admin should in the second text field from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                | sheetName |
      | VerifySecondTextField | HomePage  |

  @13HomePage_VerifySymbol_SecondTextField
  Scenario Outline: Verify asterik next to password text
    Then Admin should see symbol next to password text from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                        | sheetName |
      | VerifySymbolOfSecondTextField | HomePage  |

  @14HomePage_Verify_UserField_Alignment_
  Scenario Outline: Verify the alignment user input field for the login
    Then Admin should see input field on the centre of the page from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                    | sheetName |
      | VerifyInputFieldAlignment | HomePage  |

  @16HomePage_Verify_LoginIspresent_
  Scenario: verify Login is present
    Then Admin should see login button

  @17HomePage_Verify_LoginField_Alignment
  Scenario Outline: Verify the alignment of the login button
    Then Admin should see login button on the centre of the page from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                    | sheetName |
      | VerifyLoginFieldAlignment | HomePage  |

  @18HomePage_Verify_Login_inputDescriptive_Text
  Scenario Outline: Verify input descriptive text in user field
    Then Admin should see user color from sheet "<option>" and "<sheetName>"

    Examples: 
      | option               | sheetName |
      | VerifyInputTextColor | HomePage  |

  @19HomePage_Verify_Password_inputDescriptive_Text
  Scenario Outline: Verify input descriptive text in password field
    Then Admin should see password color from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                  | sheetName |
      | VerifyPasswordTextColor | HomePage  |
