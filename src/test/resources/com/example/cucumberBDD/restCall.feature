Feature: Belly

  Scenario: Correct non-zero number of books found by author by list
    Given I have the following details about products
      | productId | productName | price | tags          | length | width | height | latitude | longitude |
      | 123       | mobile      | 10    | "cold", "ice", "cold" | 2.2    | 3.3   | 4.3    | -9.9     | 32.3      |
      | 123       | mobile      | 11    | "cold", "ice" | 2.2    | 3.3   | 4.3    | -9.9     | 32.3      |