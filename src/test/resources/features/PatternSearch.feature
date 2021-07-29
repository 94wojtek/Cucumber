Feature: Pattern search
  Search string pattern in string text
  Determine offset number after which character is found in text

  Scenario Outline: Search pattern through different valid texts.
    Given Input pattern is <Pattern>
    And Input text is <Text>
    When Offset is calculated
    Then Offset number is <Offset>

    Examples:
    |Pattern|Text                               |Offset  |
    |rab    |abacadabrabracabracadabrabrabracad |8       |
    |pate   |tarqatratpateraq                   |9       |
    |wac    |wojwojwojwoj                       |12      |
    |woj    |wojtek                             |0       |


  Scenario: Uppercase pattern not found in lowercase text
    Given Input pattern is RAB
    And Input text is abacadabrabracabracadabrabrabracad
    When Offset is calculated
    Then Offset number is 34

  Scenario: Lowercase pattern not found in uppercase text
    Given Input pattern is rab
    And Input text is ABACADABRABRACABRACADABRABRABRACAD
    When Offset is calculated
    Then Offset number is 34

  Scenario: Invalid parameters list terminate application workflow
    Given No parameters are provided
    When App starts
    Then App is terminated