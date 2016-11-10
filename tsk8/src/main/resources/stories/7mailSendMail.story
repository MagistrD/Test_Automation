Feature: mail.ru new mail

Narrative:
As a login user
I want to send mail
So that I can check Mail.ru send mail

Scenario: Send mail
When I send new message to <mail> with <subject> and <text>
Then I want to see that mail sent

Examples:
|mail|subject|text|
|magistr-dante@mail.ru|sub|texttext|