Feature: mail.ru login

Narrative:
As a user
I want to login mail.ru
So that I can check mail.ru login

Scenario: Login Mail.ru
When I type <login> and <password>
Then I see inbox page

Examples:
|login|password|
|magistr-dante@mail.ru|09052011mail.ru|