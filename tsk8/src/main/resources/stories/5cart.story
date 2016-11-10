Feature: check Aliexpress Cart

Narrative:
As a login user
I want to perform aliexpress cart
So that I can check Aliexpress Cart functional

Scenario: Check that product add to cart
When I go to cart
Then I see product

Scenario: Deleting product from Cart
When I delete product from cart
Then I didn't see product in cart