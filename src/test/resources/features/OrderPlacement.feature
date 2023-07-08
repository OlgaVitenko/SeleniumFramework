@regression
Feature: Testing Item Purchase Functionality of the SauceDemo

  Scenario Outline: Validating Order Functionality is working for an item.(Happy Path)
    Given User provides username and oassword to login successfully
    When User chooses the '<productName>',click add to cart button and validate it is added
    And User click cart item and checkout button
    And User provides '<firstName>','<lastName>','<zipCode>' to checkout page and click continue button
    Then User validates the '<productName>','<itemTotal>','<tax>','<totalPrice>' with '8'% tax rate
    And User clicks Finish button and validate 'Thank you for your order!' for purchase
    Examples:
      | productName                       | firstName | lastName   | zipCode | itemTotal | tax  | totalPrice |
      | Sauce Labs Backpack               | Olga      | Vitenko    | 7657    | 29.99     | 2.40 | 32.39      |
      | Sauce Labs Fleece Jacket          | Mariya    | Robinovich | 76543   | 49.99     | 4.00 | 53.99      |
      | Test.allTheThings() T-Shirt (Red) | Sofia     | Petrova    | 65432   | 15.99     | 1.28 | 17.27      |

