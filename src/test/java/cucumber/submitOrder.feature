@Ecommerce
Feature: Purchase the order from Ecommerce Website

  Background: 
    Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productname> to the cart
    And Checkout <productname> and submit the order
    Then "Thankyou for the order." message is displayed on Confimation page

    Examples: 
      | name             | password    | productname     |
      | Arjun@gmail.com  | Arjun@1998@ | ZARA COAT 3     |
      | Govind@gmail.com | Govind@1    | ADIDAS ORIGINAL |
