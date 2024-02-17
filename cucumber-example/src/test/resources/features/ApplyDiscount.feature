Feature: Applying discount to a product

  Scenario Outline: Applying a discount to a product
    Given a product with name "<ProductName>", ID <ProductId>, price <ProductPrice>
    And a discount with ID <DiscountId>, <DiscountPercentage>% discount, starting on "<StartDate>", ending on "<EndDate>"
    When the discount is applied to the product
    Then the discounted price should be <ExpectedDiscountedPrice>

    Examples:
      | ProductName | ProductId | ProductPrice | DiscountId | DiscountPercentage | StartDate  | EndDate    | ExpectedDiscountedPrice |
      | Product A   | 1         | 100.00       | 1          | 10                 | 2024-02-15 | 2024-03-25 | 90.00                   |
      | Product B   | 2         | 50.00        | 2          | 20                 | 2024-02-26 | 2024-02-28 | 0.00                    |
      | Product C   | 3         | 75.00        | 3          | 15                 | 2024-02-01 | 2024-03-05 | 63.75                   |
