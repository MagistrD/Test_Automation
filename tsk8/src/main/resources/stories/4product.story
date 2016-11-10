Feature: Aliexpress adding to cart

Narrative:
As a login user
I want to add product to cart
So that I can check aliexpress adding to cart

Scenario: Adding product to Cart
Given Product cart is available
When I select <color> and <from> and send to Cart
Then I see button go to cart

Examples:
|color|from|
|White|Великобритания|
|Black|Китай|
