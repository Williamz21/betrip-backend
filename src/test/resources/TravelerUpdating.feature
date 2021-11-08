Feature: Traveler updating
  As a Developer
  I want to update a  Traveler through API
  So that It can be available to use.
  Background:
    Given The EndPoint to update "http://localhost:%d/api/v1/travelers/1" is available
  @traveler-updating
  Scenario: Updating Traveler
    When A Traveler Update Request is sent with values "Pedroedited",1,"0999999","pedro@pedro","pedrito123","987654321","pedrito.jpg"
    Then A response with status 200 is received
    And  A Traveler Resource with values "Pedroedited",1,"0999999","pedro@pedro","pedrito123","987654321","pedrito.jpg" is included in Response Body