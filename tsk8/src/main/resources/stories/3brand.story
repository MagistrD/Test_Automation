Feature: Aliexpress brand search

Narrative:
As a login user
I want to search by brand
So that I can check selecting product by brand search

Scenario: Search by Brand Name
When I type brand to search
Then I see brand page

Scenario: Select Product
Given Brand page is available
When I select product
Then I see product page
