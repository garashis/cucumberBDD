Feature: Belly

  Scenario Outline: test rest call
    Given I want to test <event> event
    Examples:
      | event       |
      | addUser     |