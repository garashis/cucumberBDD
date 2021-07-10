Feature: Belly

  Scenario: I am in a scenario
    Given I have the following details about products
      | productId | productName | price | tags          | length | width | height | latitude | longitude |
      | 123       | mobile      | 10    | "cold", "ice" | 2.2    | 3.3   | 4.3    | -9.9     | 32.3      |
      | 123       | mobile      | 11    | "cold", "ice" | 2.2    | 3.3   | 4.3    | -9.9     | 32.3      |


  Scenario Outline: I am testing <scenario> scenario
    Given product details in JSON template and validate
      | jsonTemplate | product       |
      | scenario     | <scenario>    |
      | productId    | <productId>   |
      | productName  | <productName> |
      | price        | <price>       |
      | tags         | <tags>        |
      | length       | <length>      |
      | width        | <width>       |
      | height       | <height>      |
      | latitude     | <latitude>    |
      | longitude    | <longitude>   |
    Examples:
      | scenario | productId | productName | price | tags            | length | width | height | latitude | longitude |
      | positive | 123       | mobile      | 10    | "cold", "ice"   | 2.2    | 3.3   | 4.3    | -9.9     | 32.3      |
      | negative | 234       | watch       | 11    | "rado", "white" | 2.2    | 3.3   | 4.3    | -9.9     | 32.3      |